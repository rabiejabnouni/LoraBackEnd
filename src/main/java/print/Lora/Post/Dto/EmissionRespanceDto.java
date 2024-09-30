package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.DTO.ReactRespanceDTO;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class EmissionRespanceDto {

    private String sender;
    private ReactRespanceDTO react;
    private LocalDateTime createdAt;
    private LocalDateTime heureDebut;
    private String description;
    private String lienEmission;
    private int dureeEnMinutes;
    private boolean enDirect;
    private String contexte;

    // Getters and setters
}
