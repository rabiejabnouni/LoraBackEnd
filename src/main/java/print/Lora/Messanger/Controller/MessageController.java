package print.Lora.Messanger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Messanger.DTO.MessageRespanceDTO;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Envoi d'un message
    @PostMapping("/send")
    public ResponseEntity<MessageRespanceDTO> sendMessage(@RequestParam long senderId,
                                                          @RequestParam long conversionId,
                                                          @RequestBody String messageContent) {
        MessageRespanceDTO message = messageService.sendMessage(senderId, messageContent, conversionId);
        return ResponseEntity.ok(message);
    }

    // Récupération des messages par ID de conversation
    @GetMapping("/conversation/{conversionId}")
    public ResponseEntity<List<MessageRespanceDTO>> getMessagesByConversation(@PathVariable long conversionId) {
        List<MessageRespanceDTO> messages = messageService.getMessagesByConversation(conversionId);
        return ResponseEntity.ok(messages);
    }

    // Marquer un message comme vu
    @PutMapping("/seen/{messageId}")
    public ResponseEntity<String> markMessageAsSeen(@PathVariable long messageId) {
        messageService.markMessageAsSeen(messageId);
        return ResponseEntity.ok("Message marked as seen");
    }
}
