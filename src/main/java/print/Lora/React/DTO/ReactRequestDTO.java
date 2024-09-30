package print.Lora.React.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.React.Model.ReactType;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactRequestDTO {

    private  long PostId;
    private String type;
}
