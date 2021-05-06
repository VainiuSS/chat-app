package lt.vainius.chatapp.service;

import lt.vainius.chatapp.model.ChatMessage;
import lt.vainius.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void save(ChatMessage message){
        messageRepository.save(message);
    }
    public ChatMessage saveAndReturn(ChatMessage message){
        return messageRepository.saveAndFlush(message);
    }
    public Optional<ChatMessage> find(Integer id){
        return messageRepository.findById(id);
    }
    public List<ChatMessage> findAllBySenderId(Integer senderId){
        return messageRepository.findAllBySenderId(senderId);
    }


}
