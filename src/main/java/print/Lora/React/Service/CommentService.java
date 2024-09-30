package print.Lora.React.Service;


import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.ReactEntity;

import java.util.List;

public interface CommentService {
    void save(CommentEntity comment);

    List<CommentEntity> findAll();

    List<CommentEntity> findByReact(ReactEntity react);
}
