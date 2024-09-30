package print.Lora.React.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import print.Lora.Auth.Model.AppUser;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    @Query("SELECT l FROM CommentEntity l WHERE l.react=?1")
    List<CommentEntity> findByIdReact(ReactEntity react);
    @Query("SELECT l FROM CommentEntity l WHERE l.react=?1")
    List<CommentEntity> findByIduSender(AppUser sender);
}
