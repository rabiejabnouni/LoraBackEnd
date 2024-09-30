package print.Lora.Post.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print.Lora.Post.Entity.Reunion;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {
}

