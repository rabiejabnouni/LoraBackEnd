package print.Lora.React.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Service.ConversionService;
import print.Lora.React.Model.*;
import print.Lora.React.Service.ReactService;

import java.time.LocalDateTime;

import static print.Lora.React.Model.ReactType.message;
import static print.Lora.React.Model.ReactType.post;

@Service
public class LikeMapper {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ReactService reactService;
    public LikeRespanceDTO EntityToRespance(LikeEntity like){

        LikeRespanceDTO likeRespanceDTO = new LikeRespanceDTO();

        likeRespanceDTO.setSenderName(like.getId().getSender().getUsername());

        likeRespanceDTO.setType(like.getType());

        likeRespanceDTO.setSendAt(like.getSendDate());

        likeRespanceDTO.setIdReact(like.getId().getReact().getIdr());
        return  likeRespanceDTO;
    }
    public LikeEntity RequestToEntity(LikeRequestDTO like){
       AppUser sender = appUserService.findByid(like.getSenderId());
       if(sender==null){
           throw new RuntimeException("user not found");
       }
       ReactEntity react = reactService.findById(like.getIdReact());
        if(react==null){
            throw new RuntimeException("react not found");
        }
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setId(new LikeId(sender,react));
        likeEntity.setType(stringToType(like.getType()));
        likeEntity.setSendDate(LocalDateTime.now());
        return  likeEntity;
    }

    public TypeLike stringToType(String type) {
        switch (type) {
            case "ADORE":
                return TypeLike.ADORE;
            case "FACHE":
                return TypeLike.FACHE;
            case "TRISTE":
                return TypeLike.TRISTE;
            case "RIRE":
                return TypeLike.RIRE;
            case "J_AIME":
                return TypeLike.J_AIME;
            default:
                throw new RuntimeException("Erreur : type inconnu");
        }
    }


}
