package print.Lora.Post.Service;

import print.Lora.Post.Dto.ReunionRequestDto;
import print.Lora.Post.Dto.ReunionResponseDto;

import java.util.List;

public interface ReunionService {

    ReunionResponseDto createReunion(ReunionRequestDto requestDto);

    ReunionResponseDto updateReunion(Long reunionId, ReunionRequestDto requestDto);

    ReunionResponseDto getReunionById(Long reunionId);

    List<ReunionResponseDto> getAllReunions();

    void deleteReunion(Long reunionId);
}

