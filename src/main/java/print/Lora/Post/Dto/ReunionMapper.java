package print.Lora.Post.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Post.Entity.Reunion;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Repository.ReactRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReunionMapper {
    @Autowired
    private ReactRepository reactRepository;
    @Autowired
    private AppUserService appUserService;

    // Convertir le DTO Request en entité Reunion
    public Reunion requestToEntity(ReunionRequestDto requestDto) {
        Reunion reunion = new Reunion();
        reunion.setSender(appUserService.findByid(requestDto.getSender()));
        reunion.setImagePath(requestDto.getImagePath());
        reunion.setDescription(requestDto.getDescription());
        reunion.setLienReunion(requestDto.getLienReunion());
        ReactEntity react = new ReactEntity(requestDto.getSender(), ReactType.post);
        ReactEntity newReact =reactRepository.save(react);
        reunion.setReactId(newReact.getIdr());// Trouver l'utilisateur par ID
        // Récupérer les utilisateurs à partir de leurs identifiants et les ajouter à la réunion
        List<AppUser> partisans = requestDto.getPartisansIds().stream()
                .map(id -> appUserService.findByid(id))  // Trouver chaque utilisateur par son id
                .collect(Collectors.toList());
        reunion.setPartisans(partisans);

        return reunion;
    }

    // Convertir l'entité Reunion en DTO Response
    public ReunionResponseDto entityToResponse(Reunion reunion) {
        ReunionResponseDto responseDto = new ReunionResponseDto();
        responseDto.setImagePath(reunion.getImagePath());
        responseDto.setDescription(reunion.getDescription());
        responseDto.setLienReunion(reunion.getLienReunion());

        // Récupérer les noms d'utilisateur des participants
        List<String> partisansUsernames = reunion.getPartisans().stream()
                .map(AppUser::getUsername)
                .collect(Collectors.toList());
        responseDto.setPartisansUsernames(partisansUsernames);

        return responseDto;
    }
}

