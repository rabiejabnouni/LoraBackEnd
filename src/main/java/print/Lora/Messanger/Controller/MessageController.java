package print.Lora.Messanger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Messanger.DTO.MessageRequestDTO;
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
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequestDTO requestDTO) {
        System.out.println("hello" );
         messageService.sendMessage(requestDTO);
        return ResponseEntity.ok("ok");
    }

    // Récupération des messages par ID de conversation
    @GetMapping("/conversation")
    public ResponseEntity<List<MessageRespanceDTO>> getMessagesByConversation(@RequestParam long conversionId) {
        List<MessageRespanceDTO> messages = messageService.getMessagesByConversation(conversionId);
        return ResponseEntity.ok(messages);
    }

    // Marquer un message comme vu
    @PutMapping("/seen")
    public ResponseEntity<String> markMessageAsSeen(@RequestParam long messageId) {
        messageService.markMessageAsSeen(messageId);
        return ResponseEntity.ok("Message marked as seen");
    }
    @GetMapping
    public List<MessageRespanceDTO> getAll(){
        return messageService.getAll();
    }

}
