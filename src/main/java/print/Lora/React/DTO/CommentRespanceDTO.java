package print.Lora.React.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentRespanceDTO {

    private String senderName;
    private LocalDateTime sendDate;
    private String comment;
    private long idReact;
    private String imagePath;
    private String contexte;

}
