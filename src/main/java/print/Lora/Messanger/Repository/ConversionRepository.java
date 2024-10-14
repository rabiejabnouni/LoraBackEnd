package print.Lora.Messanger.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Messanger.Model.ConversionEntity;

import java.util.List;

public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {
    @Query("SELECT c FROM ConversionEntity c " +
            "JOIN c.betwinUsers u " +
            "WHERE c.createBy = :creator AND :otherUser MEMBER OF c.betwinUsers")
    ConversionEntity findByCreatorAndOtherUser(@Param("creator") String creator, @Param("otherUser") AppUser otherUser);
    @Query("SELECT c FROM ConversionEntity c WHERE c.createBy=?1")
    List<ConversionEntity> findByCreator(String creator);

}
