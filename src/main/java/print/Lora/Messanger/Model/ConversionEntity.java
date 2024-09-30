package print.Lora.Messanger.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Model.UserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ConversionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "conversion_users",
            joinColumns = @JoinColumn(name = "conversion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> betwinUsers = new ArrayList<>();

    private String createBy;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "conversion", cascade = CascadeType.ALL)
    private List<MessageEntity> messages = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime lastmsgAt;

    public ConversionEntity(String description, LocalDateTime createAt) {
        this.description = description;
        this.createAt = createAt;
    }

    public ConversionEntity(String createBy) {
        this.createBy = createBy;
        this.createAt = LocalDateTime.now();
        this.description = " ";
        this.lastmsgAt = LocalDateTime.now();
    }
}
