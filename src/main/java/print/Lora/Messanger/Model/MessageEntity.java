package print.Lora.Messanger.Model;

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
public class MessageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false) // This creates the foreign key column `sender_id`
    private AppUser sender;

    @ManyToOne
    @JoinColumn(name = "conversion_id", nullable = false) // Foreign key for conversion
    private ConversionEntity conversion;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime sendAt;

    private LocalDateTime vuAt;

    private String imagePath;

    private String context;

    public MessageEntity(AppUser sender ,String message , String context, ConversionEntity conversion) {
        this.sender =sender;
        this.message = message;
        this.sendAt = LocalDateTime.now();
        this.context = context;
        this.conversion = conversion;
    }

}
