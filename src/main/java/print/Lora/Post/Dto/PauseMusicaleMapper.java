package print.Lora.Post.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public PauseMusicale RequestToEntity(PauseMusicaleRequestDto request){
        PauseMusicale pause = new PauseMusicale();
        AppUser sender = appUserService.findByid(request.getSender());
        ReactEntity react = new ReactEntity(request.getSender(), ReactType.post);
        ReactEntity newReact =reactRepository.save(react);
        pause.setReactId(newReact.getIdr());
        pause.setChansons(request.getSongPath());
        pause.setPathImage(request.getImagePath());
        pause.setCreatedAt(LocalDateTime.now());
        pause.setPlayed(false);
        pause.setSender(sender);
        System.out.println(newReact.getIdr());

        pause.setDescription(request.getDescription());
        pause.setContexte(contexteService.contexte(request.getDescription()));

        return pause;

    }


    public PauseMusicaleRespanceDTO EntityToRespance(PauseMusicale pause){
        PauseMusicaleRespanceDTO respance = new PauseMusicaleRespanceDTO();
        ReactRespanceDTO react = reactMapper.EntityToRespance(reactService.findById(pause.getReactId()));
        respance.setDescription(pause.getDescription());
        respance.setSender(pause.getSender().getUsername());
        respance.setImagePath(pause.getPathImage());
        respance.setSongPath(pause.getChansons());
        respance.setReact(react);
        respance.setPlyedAt(pause.getDateDePause());

        return respance;
    }
}
