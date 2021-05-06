package lt.vainius.chatapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "senderid")
    private Integer senderId;
    @Column(name = "sender")
    private String sender;
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "sentdate")
    private Date sentDate;
    @Transient
    private MessageType type;

    public ChatMessage() {
    }

    public enum MessageType {
        CHAT, JOIN, LEAVE, HISTORY
    }

}
