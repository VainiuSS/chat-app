package lt.vainius.chatapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @CreationTimestamp
    @Column(name = "creationdate")
    Timestamp creationDate;
}
