package print.Lora.React.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

   @Override
   public void save(CommentEntity comment){
       commentRepository.save(comment);
   }
   @Override
    public List<CommentEntity> findAll(){
       return  commentRepository.findAll();
   }
    @Override
    public List<CommentEntity> findByReact(ReactEntity react){
        return commentRepository.findByIdReact(react);
    }
}
