package print.Lora.React.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;

import java.util.List;
import java.util.Optional;

public interface ReactRepository extends JpaRepository<ReactEntity, Long> {

    @Query("SELECT m FROM ReactEntity m WHERE m.idPost=?1 AND m.reactType=?2")
    Optional<ReactEntity> findByid(long id, ReactType type);
    @Modifying
    @Transactional  // Ensure this is transactional for write operations
    @Query("UPDATE ReactEntity r SET r.likes = ?2 WHERE r.idr = ?1")
    int updateLikes(long idReact, List<LikeEntity> likes);

}
