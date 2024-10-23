package print.Lora.React.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idr;

    @OneToMany(mappedBy = "id.react", cascade = CascadeType.ALL)
    private List<LikeEntity> likes = new ArrayList<>() ;

    @OneToMany(mappedBy = "idc", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

    private int score;

    @Column(nullable = false)
    private long idPost;

    @Column(nullable = false)
    private ReactType reactType;


    public ReactEntity(long idPost, ReactType type) {
        this.idPost = idPost;
        this.reactType = type;
    }

    public ReactEntity(ReactType reactType) {
        this.reactType = reactType;
    }

    public ReactEntity(List<LikeEntity> likes, List<CommentEntity> comments, int score) {
        this.likes = likes;
        this.comments = comments;
        this.score = score;
    }
}
