package print.Lora.Post.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import print.Lora.Auth.Model.AppUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "reunions") // Nom de la table dans la base de données
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Reunion extends  Post  implements Serializable {

    private String imagePath;

    private String description;

    @Column(nullable = false)
    private String LienReunion;

    @ManyToMany
    @JoinTable(
            name = "reunion_users",
            joinColumns = @JoinColumn(name = "reunions_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> partisans=new ArrayList<>();


}

