package print.Lora.Messanger.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MessageRequestDTO {
    private String message;
    private long conversionId;
    private String sender;
}
