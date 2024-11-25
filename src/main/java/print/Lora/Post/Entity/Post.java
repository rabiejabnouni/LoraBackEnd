package print.Lora.Post.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;
import print.Lora.React.Model.ReactEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Définir la stratégie d'héritage
@DiscriminatorColumn(name = "post_type")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser sender;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private long reactId;

    @Column(nullable = false)
    private String contexte;

    public Post(LocalDateTime createdAt, AppUser sender, String description, long reactId, String contexte) {
        this.createdAt = createdAt;
        this.sender = sender;
        this.description = description;
        this.reactId = reactId;
        this.contexte = contexte;
    }
}
