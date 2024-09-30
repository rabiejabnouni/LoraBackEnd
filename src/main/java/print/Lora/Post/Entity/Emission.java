package print.Lora.Post.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
public class Emission extends Post  implements  Serializable{

    private LocalDateTime heureDebut;

    private String lienEmission;

    private int dureeEnMinutes;

    private boolean enDirect;



}

