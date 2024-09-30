package print.Lora.React.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.Model.TypeLike;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeRequestDTO {
    private long senderId;
    private long idReact;
    private String type;


}
