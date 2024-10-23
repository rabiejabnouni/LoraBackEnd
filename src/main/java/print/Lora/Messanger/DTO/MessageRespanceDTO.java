package print.Lora.Messanger.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class MessageRespanceDTO {

    private long id;

    private String message;

    private LocalDateTime sendAt;

    private LocalDateTime vuAt;

    private String imagePath;

    private String context;


    private String senderId;

    private long conversionId;
    private List<String> sendTO;
}
