package print.Lora.React.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Repository.LikeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeServie {

    @Autowired
    private LikeRepository likeRepository;

    @Transactional
    @Override
    public void save(LikeEntity like) {
        Optional<LikeEntity> existingLike = likeRepository.findById(like.getId());

        if (existingLike.isPresent()) {
            LikeEntity foundLike = existingLike.get();
            System.out.println("delete like");
            likeRepository.delete(foundLike);
        } else {
            System.out.println("save like");
            likeRepository.save(like);
        }
    }
    @Override
    public List<LikeEntity> findByReact(ReactEntity react){
        return likeRepository.findByIdReact(react);
    }






}