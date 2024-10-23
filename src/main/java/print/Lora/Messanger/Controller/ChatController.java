package print.Lora.Messanger.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import print.Lora.Messanger.DTO.MessageRequestDTO;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")  // Client sends here
    @SendTo("/topic/messages")       // Messages sent to subscribers
    public MessageRequestDTO sendMessage(MessageRequestDTO message, SimpMessageHeaderAccessor headerAccessor) {
        return message;  // Send message to topic
    }
}

