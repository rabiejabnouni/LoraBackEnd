package print.Lora.Messanger.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Messanger.Model.ConversionEntity;

import java.util.List;
import java.util.Optional;

public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {



    List<ConversionEntity> findByBetwinUsersContaining(AppUser user);

    @Query("SELECT c FROM ConversionEntity c JOIN c.betwinUsers u WHERE u IN :users GROUP BY c HAVING COUNT(u) = :userCount")
    Optional<ConversionEntity> findByBetwinUsersContainingAll(List<AppUser> users, Long userCount);



}
