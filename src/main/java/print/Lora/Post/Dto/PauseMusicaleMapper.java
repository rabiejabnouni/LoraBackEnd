package print.Lora.Post.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.React.DTO.ReactMapper;
import print.Lora.React.DTO.ReactRespanceDTO;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Repository.ReactRepository;
import print.Lora.React.Service.ReactService;
import print.Lora.Service.ContexteService;

import java.io.IOException;
import java.time.LocalDateTime;
@Service
public class PauseMusicaleMapper {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ReactService reactService;
    @Autowired
    private ContexteService contexteService;
    @Autowired
    private ReactMapper reactMapper;
    @Autowired
    private ReactRepository reactRepository;
    public PauseMusicale RequestToEntity(PauseMusicaleRequestDto request) {
        System.out.println("heloo");

        PauseMusicale pause = new PauseMusicale(LocalDateTime.now()
                                   ,appUserService.UserByUsername(request.getSender())
                                   ,request.getDescription()
                                   ,0
                                   ,contexteService.contexte(request.getDescription())
                                   ,LocalDateTime.now()//TODO FIND THE TIME OF PLAY
                                    ,Boolean.FALSE
                                     ,request.getImagePath()
                                     ,request.getSongPath()
                                     ,request.getLength()
        );
        System.out.println("heloo");




        return pause;

    }


    public PauseMusicaleRespanceDTO EntityToRespance(PauseMusicale pause){
        PauseMusicaleRespanceDTO respance = new PauseMusicaleRespanceDTO();
        respance.setId(pause.getId());

        respance.setLength(pause.getLength());
        respance.setCreateAt(pause.getCreatedAt());
        respance.setDescription(pause.getDescription());
        respance.setSender(pause.getSender().getUsername());
        respance.setImagePath(pause.getImageData());
        respance.setSongPath(pause.getSongPath());
        respance.setReact(pause.getReactId());
        respance.setIsPlayed(pause.getPlayed());
        respance.setPlayedAt(pause.getDateDePause());

        return respance;
    }

}
