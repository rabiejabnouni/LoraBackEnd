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
    public PauseMusicale RequestToEntity(PauseMusicaleRequestDto request) throws IOException {
        PauseMusicale pause = new PauseMusicale();
        System.out.println(pause);
        AppUser sender = appUserService.UserByUsername(request.getSender());
        ReactEntity react = new ReactEntity( ReactType.post);
        ReactEntity newReact =reactRepository.save(react);
        pause.setReactId(newReact.getIdr());
        pause.setSongPath( convertMultipartFileToByteArray(request.getSongPath()));
        pause.setImageData(convertMultipartFileToByteArray(request.getImageData()));
        pause.setCreatedAt(LocalDateTime.now());
        pause.setPlayed(false);
        pause.setSender(sender);
        pause.setLength(request.getLength());

        pause.setDescription(request.getDescription());
        pause.setContexte(contexteService.contexte(request.getDescription()));

        return pause;

    }


    public PauseMusicaleRespanceDTO EntityToRespance(PauseMusicale pause){
        PauseMusicaleRespanceDTO respance = new PauseMusicaleRespanceDTO();
        respance.setId(pause.getId());

        respance.setLength(pause.getLength());
        respance.setCreateAt(pause.getCreatedAt());
        respance.setDescription(pause.getDescription());
        respance.setSender(pause.getSender().getUsername());
        respance.setImageData(pause.getImageData());
        respance.setSongPath(pause.getSongPath());
        respance.setReact(pause.getReactId());
        respance.setIsPlayed(pause.getPlayed());
        respance.setPlayedAt(pause.getDateDePause());

        return respance;
    }
    public  byte[] convertMultipartFileToByteArray(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide ou nul.");
        }
        // Utilise la méthode getBytes() pour convertir en byte[]
        return file.getBytes();
    }
}
