package print.Lora.Messanger.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequestDTO {
    private String message;
    private long conversionId;
    private String sender;
    private MessageType type;
}
