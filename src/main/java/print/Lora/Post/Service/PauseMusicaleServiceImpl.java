package print.Lora.Post.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Post.Dto.PauseMusicaleMapper;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Dto.PauseMusicaleRequestDto;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;
import print.Lora.Post.Repository.PauseMusicaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PauseMusicaleServiceImpl implements PauseMusicaleService {

    @Autowired
    private PauseMusicaleRepository pauseMusicaleRepository;

    @Autowired
    private PauseMusicaleMapper pauseMusicaleMapper;

    @Override
    public PauseMusicale createPauseMusicale(PauseMusicaleRequestDto requestDto) {
        PauseMusicale pauseMusicale = pauseMusicaleMapper.RequestToEntity(requestDto);
        return pauseMusicaleRepository.save(pauseMusicale);
    }

    @Override
    public List<PauseMusicale> getAllPauseMusicales() {
        return pauseMusicaleRepository.findAll();
    }

    @Override
    public Optional<PauseMusicale> getPauseMusicaleById(Long id) {
        return pauseMusicaleRepository.findById(id);
    }

    @Override
    public PauseMusicale updatePauseMusicale(Long id, PauseMusicaleRequestDto requestDto) {
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
}

