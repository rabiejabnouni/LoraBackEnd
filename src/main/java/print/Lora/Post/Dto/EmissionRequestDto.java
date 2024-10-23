package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class EmissionRequestDto {

    private long sender;
    private String description;
    private LocalDateTime heureDebut;
    private String lienEmission;
    private int dureeEnMinutes;
    private boolean enDirect;
    private long reactId;
    private String contexte;

}
