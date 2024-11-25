package print.Lora.Post.Service;

import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Dto.PauseMusicaleRequestDto;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PauseMusicaleService {
    PauseMusicale createPauseMusicale(PauseMusicaleRequestDto requestDto) ;
    List<PauseMusicaleRespanceDTO> getAllPauseMusicales();
    Optional<PauseMusicale> getPauseMusicaleById(Long id);
    PauseMusicale updatePauseMusicale(Long id, PauseMusicaleRequestDto requestDto);
    void deletePauseMusicale(Long id);
    PauseMusicaleRespanceDTO convertToDto(PauseMusicale pauseMusicale);

    void played(long id,boolean isPlayed);

    void setCreationDate(long id);
}

