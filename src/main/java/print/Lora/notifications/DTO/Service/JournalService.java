package print.Lora.notifications.DTO.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Post.Dto.PauseMusicaleMapper;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;
import print.Lora.Post.Dto.PostRespanceDto;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Repository.PauseMusicaleRepository;
import print.Lora.Post.Service.PauseMusicaleService;
import print.Lora.Post.Service.PostService;
import print.Lora.notifications.DTO.Entity.JournalEntity;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JournalService {

    @Autowired
    private PauseMusicaleRepository pauseMusicale;



    @Autowired
    private PauseMusicaleService service;

    @Autowired
    private PostService postService;

    @Autowired
    private PauseMusicaleMapper pauseMusicaleMapper;

    // Méthode pour gérer les posts (inchangée)
    public List<PostRespanceDto> journalOfPost() {
        List<PostRespanceDto> responseDtos = postService.getAllPosts();
        return responseDtos.stream()
                .sorted(Comparator.comparing(PostRespanceDto::getCreatedAt))
                .collect(Collectors.toList());
    }

    // Méthode pour gérer les pauses musicales
    public List<Map<Long, List<PauseMusicaleRespanceDTO>>> journalOfBreak() {
        List<Map<Long, List<PauseMusicaleRespanceDTO>>> pauses = new ArrayList<>();

        // Retrieve all pause entities that have not been played
        List<PauseMusicale> pauseEntities = pauseMusicale.findAll().stream()
                .filter(pauseMusicale -> !pauseMusicale.getPlayed())
                .sorted(Comparator.comparing(PauseMusicale::getCreatedAt))
                .collect(Collectors.toList());

        // If no pause entities are found, mark all as not played and retrieve them
        if (pauseEntities.isEmpty()) {
            pauseEntities = pauseMusicale.findAll().stream()
                    .peek(pause -> service.played(pause.getId(), false)) // Set all to not played
                    .sorted(Comparator.comparing(PauseMusicale::getCreatedAt))
                    .collect(Collectors.toList());
        }

        long pauseNumber = 0;
        List<Long> idPause = new ArrayList<>();

        // Group pauses into lists based on total length
        while ( pauseNumber <= 5) {
            List<PauseMusicaleRespanceDTO> currentPauseList = new ArrayList<>();
            int totalLength = 0;

            while (!pauseEntities.isEmpty() && totalLength < 600) {
                PauseMusicale currentPause = pauseEntities.remove(0);
                service.setCreationDate(currentPause.getId());
                idPause.add(currentPause.getId());
                PauseMusicaleRespanceDTO dto = pauseMusicaleMapper.EntityToRespance(currentPause);
                currentPauseList.add(dto);
                totalLength += currentPause.getLength();
            }

            Map<Long, List<PauseMusicaleRespanceDTO>> map= new HashMap<>();
            map.put(pauseNumber,currentPauseList);
            pauses.add(map);
            pauseNumber++;
        }

        // Create and save JournalEntity with only IDs
        JournalEntity journal = new JournalEntity(LocalDateTime.now(), idPause, false);

        return pauses;
    }


}
