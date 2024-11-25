package print.Lora.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseMusicaleRequestDto  {

    private String sender;
    private String description;
    private String imagePath;
    private String songPath;
    private long length;
}
