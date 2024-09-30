package print.Lora.Auth.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Post.Entity.Emission;
import print.Lora.Post.Entity.Post;
import print.Lora.Post.Entity.Reunion;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.LikeEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AppUser implements Serializable {

    private LocalDateTime birthDay;

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserEntity> friends = new ArrayList<>();

    @ManyToMany(mappedBy = "betwinUsers")
    private List<ConversionEntity> conversions = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<MessageEntity> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "id.sender", cascade = CascadeType.ALL)
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany(mappedBy = "partisans")
    private List<Reunion> emissions = new ArrayList<>();

    private String imageProfilPath;


}
