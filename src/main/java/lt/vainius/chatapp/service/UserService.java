package lt.vainius.chatapp.service;

import lt.vainius.chatapp.model.ChatMessage;
import lt.vainius.chatapp.model.User;
import lt.vainius.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(ChatMessage chatMessage) {
        // We treat each connecting user as a new user for simplicity
        User user = new User();
        user.setName(chatMessage.getSender());
        userRepository.saveAndFlush(user);
        return user;
    }
}
