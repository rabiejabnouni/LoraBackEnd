package print.Lora.Post.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Service.AppUserService;

import print.Lora.Post.Entity.Post;
import print.Lora.React.DTO.ReactMapper;
import print.Lora.React.Model.ReactEntity;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Repository.ReactRepository;
import print.Lora.React.Service.ReactService;

import java.time.LocalDateTime;

@Service
public class PostMapper {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ReactRepository reactRepository;
    @Autowired
    private ReactService reactService;

    @Autowired
    private ReactMapper reactMapper;

    // Convertir la requête DTO en entité Post
    public Post requestToEntity(PostRequestDto requestDto) {
        Post post = new Post();
        post.setSender(appUserService.findByid(requestDto.getSenderId()));
        ReactEntity react = new ReactEntity(requestDto.getSenderId(), ReactType.post);
        ReactEntity newReact =reactRepository.save(react);
        post.setReactId(newReact.getIdr());// Trouver l'utilisateur par ID
        post.setDescription(requestDto.getDescription());
        post.setReactId(requestDto.getReactId());
        post.setContexte(requestDto.getContexte());
        post.setCreatedAt(LocalDateTime.now());  // Définit l'heure actuelle pour la création du post

        return post;
    }

    // Convertir une entité Post en réponse DTO
    public PostRespanceDto entityToRespance(Post post) {
        PostRespanceDto respanceDto = new PostRespanceDto();
        respanceDto.setId(post.getId());
        respanceDto.setSenderUsername(post.getSender().getUsername());  // Extraire le nom d'utilisateur de l'entité AppUser
        respanceDto.setDescription(post.getDescription());
        respanceDto.setCreatedAt(post.getCreatedAt());
        respanceDto.setContexte(post.getContexte());
        // Récupérer et mapper la réaction associée au post
        ReactEntity reactEntity = reactService.findById(post.getReactId());
        respanceDto.setReact(reactMapper.EntityToRespance(reactEntity));

        return respanceDto;
    }
}
