package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ReunionResponseDto {

    private String imagePath;
    private String description;
    private String lienReunion;
    private List<String> partisansUsernames;  // Liste des noms d'utilisateur des participants

    // Getters et Setters
}

