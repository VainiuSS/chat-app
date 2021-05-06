package lt.vainius.chatapp.repository;

import lt.vainius.chatapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
