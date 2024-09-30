package print.Lora.React.Service;



import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import print.Lora.React.DTO.*;
import print.Lora.React.Model.*;
import print.Lora.React.Repository.ReactRepository;
import print.Lora.Service.ContexteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactServiceImpl implements  ReactService {


    @Autowired
    private ContexteService contexteService;

    @Autowired
    private ReactRepository reactRepository;

    @Autowired
    private LikeServie likeServie;

    @Autowired
    private CommentService commentService;

    @Autowired
    @Lazy
    private LikeMapper likeMapper;

    @Autowired
    @Lazy
    private CommentMapper commentMapper;
    @Autowired
    @Lazy
    private ReactMapper reactMapper;

   @Transactional
    @Override
    public ReactRespanceDTO create(long id,ReactType type){
       if(reactRepository.findByid(id,type).isPresent()){
           throw new RuntimeException("react a real exist");
       }
        ReactEntity Msg = new ReactEntity(id,type);
        reactRepository.save(Msg);
        return reactMapper.EntityToRespance(Msg);
    }
    @Override
    public List<ReactRespanceDTO> found() {
        List<ReactEntity> reactEntities = reactRepository.findAll();

        // Convertir chaque entité ReactEntity en ReactRespanceDTO
        return reactEntities.stream()
                .map(reactMapper::EntityToRespance)
                .collect(Collectors.toList());
    }

    @Override
    public ReactEntity findById(long id) {
        return reactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReactEntity with ID " +id+ " not found"));
    }

    @Override
    public ReactRespanceDTO foundById(long id , ReactType type){
        return reactMapper.EntityToRespance(reactRepository.findByid( id , type).get());
    }
    @Override
    @Transactional
    public int LikeMessage(LikeRequestDTO likeRequestDTO) {
        LikeEntity like = likeMapper.RequestToEntity(likeRequestDTO);
        likeServie.save(like);
        return addLikeToReact(like);
    }


    @Override
    @Transactional
    public int CommentMessage(CommentRequestDTO commentRequestDTO) {
      CommentEntity comment = commentMapper.RequestToEntity(commentRequestDTO);
        if(comment.getReact() != null) {
            String contexte = contexteService.contexte(comment.getMsg());  // Utilisation du service ici
            comment.setContexte(contexte);
            comment.setSendDate(LocalDateTime.now());
            commentService.save(comment);
            return comment.getReact().getComments().size();
        }
        throw new RuntimeException("React not found");

    }
    @Override
    public List<CommentRespanceDTO> findAllComment(){
        List<CommentEntity> commentEntities =commentService.findAll();
        return commentEntities.stream().map(commentMapper::EntityToRespance).collect(Collectors.toList());
    }

    @Override
    public int addLikeToReact(LikeEntity like){
       return likeServie.findByReact(like.getId().getReact()).size();
    }
    @Override
    public int addCommentToReact(CommentEntity comment){
        ReactEntity react = reactRepository.findById(comment.getReact().getIdr()).get();
        react.getComments().add(comment);
        return react.getComments().size();
    }


}