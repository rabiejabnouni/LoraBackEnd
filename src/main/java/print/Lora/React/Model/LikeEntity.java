package print.Lora.React.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeEntity implements Serializable {

    @EmbeddedId
    private LikeId id;

   @Column(nullable = false)
   private TypeLike type;


    @Column(nullable = false)
    private LocalDateTime sendDate;

}
