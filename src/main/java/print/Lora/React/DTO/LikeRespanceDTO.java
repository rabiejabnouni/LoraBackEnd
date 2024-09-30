package print.Lora.React.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.Model.TypeLike;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class LikeRespanceDTO {
    private String senderName;
    private TypeLike type;
    private long idReact;
    private LocalDateTime sendAt;
}
