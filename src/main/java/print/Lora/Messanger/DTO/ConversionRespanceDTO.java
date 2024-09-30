package print.Lora.Messanger.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRespanceDTO {

    private String description;
    private LocalDateTime createAt;
    private LocalDateTime lastMsgAt;
    private List<MessageRespanceDTO> messageRespanceDTOS=new ArrayList<>();
}
