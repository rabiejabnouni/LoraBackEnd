package print.Lora.Post.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Post.Dto.ReunionMapper;
import print.Lora.Post.Dto.ReunionRequestDto;
import print.Lora.Post.Dto.ReunionResponseDto;
import print.Lora.Post.Entity.Reunion;
import print.Lora.Post.Repository.ReunionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReunionServiceImpl implements ReunionService {

    @Autowired
    private ReunionRepository reunionRepository;

    @Autowired
    private ReunionMapper reunionMapper;

    @Autowired
    private AppUserService appUserService;

    @Override
    public ReunionResponseDto createReunion(ReunionRequestDto requestDto) {
        Reunion reunion = reunionMapper.requestToEntity(requestDto);
        Reunion savedReunion = reunionRepository.save(reunion);
        return reunionMapper.entityToResponse(savedReunion);
    }

    @Override
    public ReunionResponseDto updateReunion(Long reunionId, ReunionRequestDto requestDto) {
        Reunion existingReunion = reunionRepository.findById(reunionId)
                .orElseThrow(() -> new RuntimeException("Réunion introuvable"));
        Reunion updatedReunion = reunionMapper.requestToEntity(requestDto);
        updatedReunion.setId(existingReunion.getId());  // Conserver l'ID existant
        Reunion savedReunion = reunionRepository.save(updatedReunion);
        return reunionMapper.entityToResponse(savedReunion);
    }

    @Override
    public ReunionResponseDto getReunionById(Long reunionId) {
        Reunion reunion = reunionRepository.findById(reunionId)
                .orElseThrow(() -> new RuntimeException("Réunion introuvable"));
        return reunionMapper.entityToResponse(reunion);
    }

    @Override
    public List<ReunionResponseDto> getAllReunions() {
        List<Reunion> reunions = reunionRepository.findAll();
        return reunions.stream()
                .map(reunionMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReunion(Long reunionId) {
        reunionRepository.deleteById(reunionId);
    }
}



