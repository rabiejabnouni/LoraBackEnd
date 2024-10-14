package print.Lora.TimeTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import print.Lora.TimeTable.Entity.HomeWorkEntity;

public interface HomeWorkRepository extends JpaRepository<HomeWorkEntity,Long> {
}
