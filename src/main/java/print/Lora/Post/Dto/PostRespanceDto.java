package print.Lora.Post.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.DTO.ReactRespanceDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRespanceDto {
    private Long id;
    private String senderUsername;  // Nom d'utilisateur de celui qui a créé le post
    private String description;
    private LocalDateTime createdAt;
    private ReactRespanceDTO react;  // Réaction associée au post
    private String contexte;
}

