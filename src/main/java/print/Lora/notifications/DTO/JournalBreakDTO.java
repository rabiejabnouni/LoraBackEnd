package print.Lora.notifications.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Post.Entity.PauseMusicale;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalBreakDTO {
    private List<PauseMusicale> breakMusicale;

}
