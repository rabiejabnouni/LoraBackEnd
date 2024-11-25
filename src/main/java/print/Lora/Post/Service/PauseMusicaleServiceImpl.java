package print.Lora.Post.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Post.Dto.PauseMusicaleMapper;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Dto.PauseMusicaleRequestDto;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;
import print.Lora.Post.Repository.PauseMusicaleRepository;
import print.Lora.React.Repository.ReactRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PauseMusicaleServiceImpl implements PauseMusicaleService {

    @Autowired
    private PauseMusicaleRepository pauseMusicaleRepository;

    @Autowired
    private PauseMusicaleMapper pauseMusicaleMapper;

    @Autowired
    private ReactRepository reactService;

    @Override
    public PauseMusicale createPauseMusicale(PauseMusicaleRequestDto requestDto)  {
        System.out.println(requestDto);
        PauseMusicale pauseMusicale = pauseMusicaleMapper.RequestToEntity(requestDto);
          pauseMusicale=pauseMusicaleRepository.save(pauseMusicale);

        return pauseMusicale;
    }

    @Override
    public List<PauseMusicaleRespanceDTO> getAllPauseMusicales() {

        List<PauseMusicaleRespanceDTO> respanceDTOS= pauseMusicaleRepository.findAll().stream()
                .map(pauseMusicaleMapper::EntityToRespance)
                .collect(Collectors.toList());
        return respanceDTOS;
    }

    @Override
    public Optional<PauseMusicale> getPauseMusicaleById(Long id) {
        return pauseMusicaleRepository.findById(id);
    }

    @Override
    public PauseMusicale updatePauseMusicale(Long id, PauseMusicaleRequestDto requestDto)  {
        Optional<PauseMusicale> existingPauseMusicale = pauseMusicaleRepository.findById(id);
        if (existingPauseMusicale.isPresent()) {
            PauseMusicale updatedPauseMusicale = pauseMusicaleMapper.RequestToEntity(requestDto);
            updatedPauseMusicale.setId(id); // Set the existing ID
            return pauseMusicaleRepository.save(updatedPauseMusicale);
        } else {
            throw new RuntimeException("PauseMusicale with ID " + id + " not found.");
        }
    }

    @Override
    public void deletePauseMusicale(Long id) {
        pauseMusicaleRepository.deleteById(id);
    }

    @Override
    public PauseMusicaleRespanceDTO convertToDto(PauseMusicale pauseMusicale) {
        return pauseMusicaleMapper.EntityToRespance(pauseMusicale);
    }

    @Override
    public void played(long id,boolean isPlayed){
        PauseMusicale pauseMusicale= pauseMusicaleRepository.findById(id).get();
        pauseMusicale.setPlayed(isPlayed);
        pauseMusicaleRepository.save(pauseMusicale);
    }

    @Override
    public void setCreationDate(long id){
        PauseMusicale pauseMusicale= pauseMusicaleRepository.findById(id).get();
        pauseMusicale.setCreatedAt(LocalDateTime.now());
        pauseMusicaleRepository.save(pauseMusicale);
    }
}

