package print.Lora.Messanger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import print.Lora.Messanger.Model.MessageEntity;

import java.util.List;


public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
    @Query("SELECT m FROM MessageEntity m WHERE m.conversion.id = ?1")
    List<MessageEntity> findByConversionId(long conversionId);

}
