package print.Lora.Post.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import print.Lora.Auth.Model.AppUser;
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


    private String imageData;


    private String  songPath;

    private long length;


    @ManyToOne
    @JoinColumn(name = "journal_id") // Reference to the JournalEntity
    private JournalEntity journal;


    public PauseMusicale( LocalDateTime createdAt, AppUser sender, String description, long reactId
            , String contexte, LocalDateTime dateDePause, Boolean played,
                         String imageData, String songPath, long length) {
        super(createdAt, sender, description, reactId, contexte);
        this.dateDePause = dateDePause;
        this.played = played;
        this.imageData = imageData;
        this.songPath = songPath;
        this.length = length;
    }
}
