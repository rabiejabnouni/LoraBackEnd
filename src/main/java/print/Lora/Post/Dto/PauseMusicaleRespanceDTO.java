package print.Lora.Post.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.DTO.ReactRespanceDTO;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
public class PauseMusicaleRespanceDTO {
    private  long id;
    private long  length;
    private String sender;
    private long react;
    private LocalDateTime CreateAt;
    private LocalDateTime playedAt;
    private Boolean isPlayed;
    private String description;
    private byte[] imageData;
    private byte[]  songPath;
}
