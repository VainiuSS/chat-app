package lt.vainius.chatapp.service;

import lt.vainius.chatapp.model.Attachment;
import lt.vainius.chatapp.model.ChatMessage;
import lt.vainius.chatapp.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public Attachment upload(byte[] file) {
        Attachment attachment = new Attachment();
        attachment.setContent(file);
        attachmentRepository.saveAndFlush(attachment);
        return attachment;
    }

    public Optional<Attachment> find(Integer id) {
        return attachmentRepository.findById(id);
    }

    public List<Attachment> findAfterDate(Timestamp afterTimestamp) {
        return attachmentRepository.findAllWithCreationDateTimeBefore(afterTimestamp);
    }
    public void deleteOldAttachments(Timestamp timestamp){
        attachmentRepository.deleteOldAttachments(timestamp);
    }
}
