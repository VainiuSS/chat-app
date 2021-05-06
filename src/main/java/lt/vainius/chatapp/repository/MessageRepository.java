package lt.vainius.chatapp.repository;

import lt.vainius.chatapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findAllBySenderId(Integer senderId);
}
