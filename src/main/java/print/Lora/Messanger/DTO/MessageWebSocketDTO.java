package print.Lora.Messanger.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWebSocketDTO {
    private MessageRequestDTO messageRequestDTO;
    private List<String> sendToList;
}
