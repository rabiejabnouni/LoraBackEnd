package print.Lora.React.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.Model.ReactType;

import java.util.List;
@NoArgsConstructor
@Data
public class ReactRespanceDTO {
    private long idReact;
    private List<LikeRespanceDTO> likes;
    private List<CommentRespanceDTO>  comments;
    private int score;
    private long idPost;
    private ReactType type;
}
