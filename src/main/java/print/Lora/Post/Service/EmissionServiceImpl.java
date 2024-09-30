package print.Lora.Post.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Post.Dto.EmissionMapper;
import print.Lora.Post.Dto.EmissionRequestDto;
import print.Lora.Post.Dto.EmissionRespanceDto;
import print.Lora.Post.Entity.Emission;
import print.Lora.Post.Repository.EmissionRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmissionServiceImpl implements EmissionService {

    @Autowired
    private EmissionRepository emissionRepository;

    @Autowired
    private EmissionMapper emissionMapper;

    @Override
    public EmissionRespanceDto createEmission(EmissionRequestDto emissionRequestDto) {
        Emission emission = emissionMapper.requestToEntity(emissionRequestDto);
        emissionRepository.save(emission);
        return emissionMapper.entityToRespance(emission);
    }

    @Override
    public EmissionRespanceDto getEmissionById(Long id) {
        Emission emission = emissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emission not found with id: " + id));
        return emissionMapper.entityToRespance(emission);
    }

    @Override
    public List<EmissionRespanceDto> getAllEmissions() {
        List<Emission> emissions = emissionRepository.findAll();
        return emissions.stream()
                .map(emissionMapper::entityToRespance)
                .collect(Collectors.toList());
    }

    @Override
    public EmissionRespanceDto updateEmission(Long id, EmissionRequestDto emissionRequestDto) {
        Emission emission = emissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emission not found with id: " + id));

        // Update emission details
        emission.setDescription(emissionRequestDto.getDescription());
        emission.setHeureDebut(emissionRequestDto.getHeureDebut());
        emission.setLienEmission(emissionRequestDto.getLienEmission());
        emission.setDureeEnMinutes(emissionRequestDto.getDureeEnMinutes());
        emission.setEnDirect(emissionRequestDto.isEnDirect());
        emission.setContexte(emissionRequestDto.getContexte());

        emissionRepository.save(emission);
        return emissionMapper.entityToRespance(emission);
    }

    @Override
    public void deleteEmission(Long id) {
        Emission emission = emissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emission not found with id: " + id));
        emissionRepository.delete(emission);
    }
}
