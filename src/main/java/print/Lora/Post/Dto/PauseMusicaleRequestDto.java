package print.Lora.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseMusicaleRequestDto  {

    private long sender;
    private String description;
    private List<String> imagePath;
    private List<String> songPath;

}
