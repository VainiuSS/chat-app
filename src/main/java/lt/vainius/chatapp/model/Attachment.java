package lt.vainius.chatapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Lob
    byte[] content;
    @CreationTimestamp
    Timestamp createdDate;
}
