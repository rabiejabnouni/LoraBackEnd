package print.Lora.Post.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Post.Entity.PauseMusicale;

public interface PauseMusicaleRepository extends JpaRepository<PauseMusicale, Long> {

}

