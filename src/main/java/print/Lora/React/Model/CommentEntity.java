package print.Lora.React.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idc;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id", referencedColumnName = "id"),

    })
    private AppUser sender;


    @Column(nullable = false)
    private LocalDateTime sendDate;

    @Column(nullable = false)
    private String msg;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idr", referencedColumnName = "idr"),

    })
    private ReactEntity react;

    private String imagePath;

    private String contexte;


    public CommentEntity(AppUser username, LocalDateTime sendDate, String msg, ReactEntity react, String contexte) {
        this.sender = username;
        this.sendDate = sendDate;
        this.msg = msg;
        this.react = react;
        this.contexte = contexte;
    }
}
