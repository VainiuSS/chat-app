package lt.vainius.chatapp;

import lt.vainius.chatapp.controller.ChatController;
import lt.vainius.chatapp.model.Attachment;
import lt.vainius.chatapp.model.ChatMessage;
import lt.vainius.chatapp.service.AttachmentService;
import lt.vainius.chatapp.service.MessageService;
import lt.vainius.chatapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class ChatControllerTest {

    private ChatMessage joinChat;
    private ChatMessage sendText;
    private Attachment attachment;
    @MockBean
    private ChatController chatController;
    @MockBean
    private MessageService messageService;
    @MockBean
    private AttachmentService attachmentService;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testFile.jpg").getFile());
        byte[] fileContent = Files.readAllBytes(Path.of(file.getAbsolutePath()));

        sendText = new ChatMessage();
        sendText.setSender("Test Wilkerson");
        sendText.setType(ChatMessage.MessageType.CHAT);
        sendText.setContent("Text Content");

        attachment = new Attachment();
        attachment.setContent(fileContent);
    }
//    @Test
//    public void joinChatTest() throws Exception {
//        joinChat = new ChatMessage();
//        joinChat.setSender("Test Wilkerson");
//        joinChat.setType(ChatMessage.MessageType.JOIN);
//        ChatMessage response = chatController.addUser(joinChat);
//        assertThat(response.getId()).isNotNull();
//    }
}
