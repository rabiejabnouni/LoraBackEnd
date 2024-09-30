package print.Lora.Post.Service;

import print.Lora.Post.Dto.EmissionRequestDto;
import print.Lora.Post.Dto.EmissionRespanceDto;
import print.Lora.Post.Entity.Emission;

import java.util.List;

public interface EmissionService {

    EmissionRespanceDto createEmission(EmissionRequestDto emissionRequestDto);

    EmissionRespanceDto getEmissionById(Long id);

    List<EmissionRespanceDto> getAllEmissions();

    EmissionRespanceDto updateEmission(Long id, EmissionRequestDto emissionRequestDto);

    void deleteEmission(Long id);
}
