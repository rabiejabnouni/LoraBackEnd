package print.Lora.React.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Service.CommentService;
import print.Lora.React.Service.LikeServie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactMapper {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikeServie likeServie;
    @Autowired
    private CommentService commentService;
    public ReactRespanceDTO EntityToRespance(ReactEntity react) {
        ReactRespanceDTO reactRespanceDTO = new ReactRespanceDTO();
        reactRespanceDTO.setIdReact(react.getIdr());
        reactRespanceDTO.setType(react.getReactType());
        reactRespanceDTO.setScore(react.getScore());
        reactRespanceDTO.setIdPost(react.getIdPost());
         List<LikeEntity> Likes = likeServie.findByReact(react);
        List<CommentEntity> Comments = commentService.findByReact(react);
        // Convert likes to LikeRespanceDTO
        List<LikeRespanceDTO> likes = Likes.stream()
                .map(like -> likeMapper.EntityToRespance(like))  // Assuming you have a likeMapper
                .collect(Collectors.toList());
        reactRespanceDTO.setLikes(likes);

        // Convert comments to CommentRespanceDTO
        List<CommentRespanceDTO> comments = react.getComments().stream()
                .map(commentMapper::EntityToRespance)  // Using method reference for mapping
                .collect(Collectors.toList());
        reactRespanceDTO.setComments(comments);

        return reactRespanceDTO;
    }

    public ReactType stringToType(String type) {
        switch (type) {
            case "post":
                return ReactType.post;
            case "message":
                return ReactType.message;
            default:
                throw new RuntimeException("Erreur : type inconnu");
        }
    }

}
