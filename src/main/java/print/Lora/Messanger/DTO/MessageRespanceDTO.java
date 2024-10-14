package print.Lora.Messanger.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class MessageRespanceDTO {

    private String message;

    private LocalDateTime sendAt;

    private LocalDateTime vuAt;

    private String imagePath;

    private String context;

    private String senderId;

    private long conversionId;
}
