package print.Lora.React.Service;

import jakarta.transaction.Transactional;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;

import java.util.List;

public interface LikeServie {
    @Transactional
    void save(LikeEntity like);


    List<LikeEntity> findByReact(ReactEntity react);
}
