package print.Lora.Post.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import print.Lora.Post.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici
}
