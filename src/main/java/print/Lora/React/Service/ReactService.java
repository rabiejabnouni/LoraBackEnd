package print.Lora.React.Service;


import jakarta.transaction.Transactional;
import print.Lora.React.DTO.CommentRequestDTO;
import print.Lora.React.DTO.CommentRespanceDTO;
import print.Lora.React.DTO.LikeRequestDTO;
import print.Lora.React.DTO.ReactRespanceDTO;
import print.Lora.React.Model.CommentEntity;
import print.Lora.React.Model.LikeEntity;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;

import java.util.List;

public interface ReactService {

    ReactRespanceDTO create(long id, ReactType type);




    List<ReactRespanceDTO> found();

    ReactEntity findById(long id);

    ReactRespanceDTO foundById(long id , ReactType type);

    @Transactional
    int LikeMessage(LikeRequestDTO like);

    @Transactional
    int CommentMessage(CommentRequestDTO commentRequestDTO);

    List<CommentRespanceDTO> findAllComment();

    int addLikeToReact(LikeEntity like);

    int addCommentToReact(CommentEntity comment);
}
