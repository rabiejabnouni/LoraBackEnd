package print.Lora.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private Long senderId;  // ID de l'utilisateur qui envoie le post
    private String description;
    private long reactId;
    private String contexte;
}
