package lt.vainius.chatapp.repository;

import lt.vainius.chatapp.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    @Query("select file from Attachment file where file.createdDate >= :createdDate")
    List<Attachment> findAllWithCreationDateTimeBefore(@Param("createdDate") Timestamp createdDate);

    @Query("delete from Attachment file where file.createdDate <= :createdDate")
    void deleteOldAttachments(@Param("createdDate") Timestamp createdDate);
}
