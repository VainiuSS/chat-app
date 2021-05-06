package lt.vainius.chatapp.controller;

import lt.vainius.chatapp.component.WebSocketEventListener;
import lt.vainius.chatapp.model.Attachment;
import lt.vainius.chatapp.model.ChatMessage;
import lt.vainius.chatapp.model.User;
import lt.vainius.chatapp.repository.AttachmentRepository;
import lt.vainius.chatapp.service.AttachmentService;
import lt.vainius.chatapp.service.MessageService;
import lt.vainius.chatapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AttachmentService attachmentService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    @Transactional
    @PostMapping(path = "message")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messageService.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.uploadAttachment")
    @SendTo("/topic/public")
    @Transactional
    public Attachment uploadAttachment(@Payload byte[] file) {
        return attachmentService.upload(file);
    }

    @MessageMapping("/chat.downloadAttachmentById")
    @SendTo("/topic/public")
    public Optional<Attachment> downloadAttachmentById(@Payload Integer id) {
        return attachmentService.find(id);
    }

    @MessageMapping("/chat.downloadAttachmentAfterTime")
    @SendTo("/topic/public")
    @Transactional
    public List<Attachment> downloadAttachmentByTime(@Payload Timestamp afterTimestamp) {
        return attachmentService.findAfterDate(afterTimestamp);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage) {
        User user = userService.addUser(chatMessage);
        Integer userId = user.getId();
        chatMessage.setSenderId(userId);
        logger.info("User " + user.getName() + " assigned ID: " + user.getId());
        return chatMessage;
    }

    @MessageMapping("/chat.getMessagesByUserId")
    @SendTo("/topic/public")
    @Transactional
    @GetMapping(path = "message/user")
    public List<ChatMessage> getUserMessages(@Payload User user) {
        logger.info("Requesting messages from userId " + user.getId());
        List<ChatMessage> messageList = messageService.findAllBySenderId(user.getId());
        for (ChatMessage message : messageList) {
            message.setType(ChatMessage.MessageType.HISTORY);
        }
        return messageList;
    }


}
