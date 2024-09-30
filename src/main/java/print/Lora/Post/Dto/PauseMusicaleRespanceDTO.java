package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.DTO.ReactRespanceDTO;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
public class PauseMusicaleRespanceDTO {

    private String sender;
    private ReactRespanceDTO react;
    private LocalDateTime plyedAt;
    private String description;
    private List<String> imagePath;
    private List<String> songPath;

}
