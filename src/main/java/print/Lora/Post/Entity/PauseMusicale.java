package print.Lora.Post.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import print.Lora.notifications.DTO.Entity.JournalEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "pause_musicale")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class PauseMusicale extends Post implements Serializable {

    private LocalDateTime dateDePause;

    private Boolean played = false;


    @Lob
    private byte[] imageData;


    @Lob
    private byte[]  songPath;

    private long length;


    @ManyToOne
    @JoinColumn(name = "journal_id") // Reference to the JournalEntity
    private JournalEntity journal;




}
