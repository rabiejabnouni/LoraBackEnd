package print.Lora.React.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Service.ReactService;

@Service
public class CommentMapper {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ReactService reactService;
    public CommentRespanceDTO EntityToRespance(CommentEntity comment){
        CommentRespanceDTO commentRespanceDTO = new CommentRespanceDTO();
        AppUser user = appUserService.findByid(comment.getSender().getId());
        commentRespanceDTO.setSenderName(user.getUsername());
        commentRespanceDTO.setComment(comment.getMsg());
        commentRespanceDTO.setContexte(comment.getContexte());
        commentRespanceDTO.setImagePath(comment.getImagePath());
        commentRespanceDTO.setIdReact(comment.getReact().getIdr());
        commentRespanceDTO.setSendDate(comment.getSendDate());
        return commentRespanceDTO;
    }
    public CommentEntity RequestToEntity(CommentRequestDTO comment){
        CommentEntity commentEntity = new CommentEntity();
        AppUser user = appUserService.findByid(comment.getSenderId());
        ReactEntity react = reactService.findById(comment.getIdReact());
        commentEntity.setSender(user);
        commentEntity.setMsg(comment.getComment());
        commentEntity.setImagePath(comment.getPathImage());
        commentEntity.setReact(react);
        return commentEntity;
    }
}
