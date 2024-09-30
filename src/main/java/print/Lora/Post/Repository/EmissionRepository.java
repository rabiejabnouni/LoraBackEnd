package print.Lora.Post.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print.Lora.Post.Entity.Emission;

@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long> {
}
