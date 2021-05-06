package lt.vainius.chatapp;


import lt.vainius.chatapp.model.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//This annotation allows this to be an integration test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketConnectionTest {
    private String URL;
    private WebSocketStompClient webSocketStompClient;
    private ChatMessage message;
    //This annotation allows this to be an integration test
    @LocalServerPort
    private Integer port;
    private ObjectMapper om;
    private BlockingQueue<ChatMessage> blockingQueue;

    @BeforeEach
    public void setup() {
        om = new ObjectMapper();
        blockingQueue = new LinkedBlockingDeque<>(1);
        message = new ChatMessage();
        message.setSender("Test Wilkerson");
        message.setType(ChatMessage.MessageType.JOIN);
        this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(
                List.of(new WebSocketTransport(new StandardWebSocketClient()))));

    }

    @Test
    public void sendToChatRoomAndListen() throws Exception {

        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session = webSocketStompClient
                .connect(getWsPath(), new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);
        assertTrue(session.isConnected());
        session.subscribe("/topic/public", new CustomStompFrameHandler());
        session.send("/topic/public", message);
        assertEquals(om.writeValueAsString(message), om.writeValueAsString(blockingQueue.poll(1, SECONDS)));
        session.disconnect();
    }

    private String getWsPath() {
        return String.format("ws://localhost:%d/ws", port);
    }

    class CustomStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return ChatMessage.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            blockingQueue.offer((ChatMessage) payload);
        }
    }
}
