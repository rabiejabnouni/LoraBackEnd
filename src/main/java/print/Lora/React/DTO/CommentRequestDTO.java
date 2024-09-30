package print.Lora.React.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDTO {
    private long senderId;
    private long idReact;
    private String comment;
    private String pathImage;
}
