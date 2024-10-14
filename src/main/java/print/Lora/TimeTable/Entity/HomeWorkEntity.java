package print.Lora.TimeTable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HomeWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sessionEntity_id")  // This is the key part that establishes the relationship
    private SessionEntity sessionEntity;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String description;

    private List<String> fiches;
}
