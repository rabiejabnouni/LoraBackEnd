package print.Lora.Messanger.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createAt;


        @OneToMany(fetch = FetchType.EAGER, mappedBy = "conversion")
        private List<MessageEntity> messages;



    @Column(nullable = false)
    private LocalDateTime lastmsgAt;

    public ConversionEntity(String description, LocalDateTime createAt) {
        this.description = description;
        this.createAt = createAt;
    }

    public ConversionEntity(String createBy) {
        this.createAt = LocalDateTime.now();
        this.description = " ";
        this.lastmsgAt = LocalDateTime.now();
    }

    public ConversionEntity(List<AppUser> betwinUsers) {
        this.betwinUsers = betwinUsers;
        this.description="hi";
    }
}
