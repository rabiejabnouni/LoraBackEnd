package print.Lora.Messanger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Messanger.DTO.ConversionRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Repository.ConversionRepository;
import print.Lora.Messanger.Service.ConversionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversionService conversionService;

    // Créer une nouvelle conversation avec une liste d'IDs d'utilisateurs
    @PostMapping("/create")
    public ResponseEntity<ConversionRespanceDTO> createConversation(@RequestParam String createdBy,
                                                               @RequestBody List<Long> userIds) {
        System.out.println(createdBy);
        ConversionRespanceDTO conversation = conversionService.createConversation(createdBy, userIds);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping
    public ResponseEntity<ConversionRespanceDTO> getByUserName(@RequestParam String create,@RequestParam String other){
        ConversionRespanceDTO respanceDTO = conversionService.getConversion(create,other);
        return  ResponseEntity.ok(respanceDTO);
    }
    // Récupérer toutes les conversations
    @GetMapping("/getAll")
    public ResponseEntity<List<ConversionRespanceDTO>> getAllConversations() {
        List<ConversionRespanceDTO> conversations = conversionService.getAllConversations();
        return ResponseEntity.ok(conversations);
    }
    @GetMapping("/getByCreator")
    public ResponseEntity<List<ConversionRespanceDTO>> getConversationByCreator(@RequestParam String creator){
        List<ConversionRespanceDTO> conversions= conversionService.getConversionByCreator(creator);
        return ResponseEntity.ok(conversions);
    }
    // Récupérer une conversation par ID
    @GetMapping("/{conversationId}")
    public ResponseEntity<ConversionRespanceDTO> getConversationById(@PathVariable long conversationId) {
        ConversionRespanceDTO conversation = conversionService.getConversationById(conversationId);
        return ResponseEntity.ok(conversation);
    }

    // Ajouter un utilisateur à une conversation
    @PutMapping("/add-user")
    public ResponseEntity<String> addUserToConversation(@RequestParam long conversationId,
                                                        @RequestParam long userId) {
        conversionService.addUserToConversation(conversationId, userId);
        return ResponseEntity.ok("User added to conversation");
    }
    @DeleteMapping
    public void delete(@RequestParam long id){
        conversionService.delete(id);
    }
}
