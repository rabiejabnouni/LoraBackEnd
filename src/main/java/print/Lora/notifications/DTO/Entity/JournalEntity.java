package print.Lora.notifications.DTO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime createAt;

    private Boolean isPlayed;

    @ElementCollection
    private List<Long> breakEntries ;// List of break entries

    public JournalEntity(LocalDateTime createAt, List<Long> breakEntries, Boolean isPlayed) {
        this.createAt = createAt;
        this.breakEntries = breakEntries;
        this.isPlayed = isPlayed;
    }
}
