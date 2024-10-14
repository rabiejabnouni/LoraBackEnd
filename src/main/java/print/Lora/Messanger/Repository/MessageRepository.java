package print.Lora.Messanger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Messanger.Model.MessageEntity;

import java.time.LocalDateTime;
import java.util.List;


public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
    @Query("SELECT m FROM MessageEntity m WHERE m.conversion.id = ?1")
    List<MessageEntity> findByConversionId(long conversionId);

    @Transactional
    @Modifying
    @Query("UPDATE MessageEntity m SET m.vuAt=?2 WHERE m.id=?1")
    void update(long id , LocalDateTime vuAt);

}
