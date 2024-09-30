package print.Lora.Post.Dto;

import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;

import print.Lora.Post.Entity.Emission;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.React.DTO.ReactMapper;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Repository.ReactRepository;
import print.Lora.React.Service.ReactService;

import java.time.LocalDateTime;

@Service
public class EmissionMapper {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ReactRepository reactRepository;
    @Autowired
    private ReactService reactService;

    @Autowired
    private ReactMapper reactMapper;

    public Emission requestToEntity(EmissionRequestDto request) {
        Emission emission = new Emission();
        AppUser sender = appUserService.findByid(request.getSender());
        ReactEntity react = new ReactEntity(request.getSender(), ReactType.post);
        ReactEntity newReact =reactRepository.save(react);
        emission.setReactId(newReact.getIdr());

        emission.setSender(sender);
        
        emission.setDescription(request.getDescription());
        emission.setHeureDebut(request.getHeureDebut());
        emission.setLienEmission(request.getLienEmission());
        emission.setDureeEnMinutes(request.getDureeEnMinutes());
        emission.setEnDirect(request.isEnDirect());
        emission.setContexte(request.getContexte());
        emission.setCreatedAt(LocalDateTime.now());

        return emission;
    }

    public EmissionRespanceDto entityToRespance(Emission emission) {
        EmissionRespanceDto response = new EmissionRespanceDto();

        response.setSender(emission.getSender().getUsername());
        response.setReact(reactMapper.EntityToRespance(reactService.findById(emission.getReactId())));
        response.setDescription(emission.getDescription());
        response.setHeureDebut(emission.getHeureDebut());
        response.setLienEmission(emission.getLienEmission());
        response.setDureeEnMinutes(emission.getDureeEnMinutes());
        response.setEnDirect(emission.isEnDirect());
        response.setContexte(emission.getContexte());
        response.setCreatedAt(emission.getCreatedAt());

        return response;
    }
}

