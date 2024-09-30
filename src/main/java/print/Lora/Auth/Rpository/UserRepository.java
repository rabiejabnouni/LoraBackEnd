package print.Lora.Auth.Rpository;

import org.springframework.data.jpa.repository.JpaRepository;
import print.Lora.Auth.Model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
