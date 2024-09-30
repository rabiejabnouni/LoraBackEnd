package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class ReunionRequestDto {
    private long sender;
    private String imagePath;
    private String description;
    private String lienReunion;
    private List<Long> partisansIds;  // Liste des identifiants des utilisateurs participant à la réunion


}

