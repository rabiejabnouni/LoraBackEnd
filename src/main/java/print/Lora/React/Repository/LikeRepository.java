package print.Lora.React.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Auth.Model.AppUser;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.LikeId;
import print.Lora.React.Model.ReactEntity;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, LikeId> {

    @Query("SELECT l FROM LikeEntity l WHERE l.id.react=?1")
    List<LikeEntity> findByIdReact(ReactEntity react);

    @Query("SELECT l FROM LikeEntity l WHERE l.id.sender=?1")
    List<LikeEntity> findByIdSender(AppUser react);
  /*  @Query("SELECT m FROM LikeEntity m WHERE m.sender=?1 AND m.react=?2")
    Optional<LikeEntity> findByid(AppUser sender, ReactEntity react);

    @Modifying
    @Transactional
    @Query("UPDATE LikeEntity l SET l.isActive = CASE WHEN l.isActive = true THEN false ELSE true END WHERE l.id = ?1")
    int updateActive(long idLike);*/

}
