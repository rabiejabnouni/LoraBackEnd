package print.Lora.Messanger.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Messanger.DTO.ConversionRespanceDTO;
import print.Lora.Messanger.DTO.userRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Repository.ConversionRepository;
import print.Lora.Messanger.Service.ConversionService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversionService conversionService;

    // Créer une nouvelle conversation avec une liste d'IDs d'utilisateurs
    @PostMapping("/create")
    public ResponseEntity<ConversionRespanceDTO> createConversation(@RequestBody List<String> userIds) {
        ConversionRespanceDTO conversation = conversionService.createConversation(userIds);
        return ResponseEntity.ok(conversation);
    }


    // Récupérer toutes les conversations
    @GetMapping("/getAll")
    public ResponseEntity<List<ConversionRespanceDTO>> getAllConversations() {
        List<ConversionRespanceDTO> conversations = conversionService.getAllConversations();
        return ResponseEntity.ok(conversations);
    }
    @GetMapping("/findUser")
    public userRespanceDTO findUser(@RequestParam String username){
        return conversionService.findUser(username);
    }
        @GetMapping("/getByCreator")
    public ResponseEntity<List<ConversionRespanceDTO>> getConversationByCreator(@RequestParam String creator){
        List<ConversionRespanceDTO> conversions= conversionService.getConversionByCreator(creator);
        return ResponseEntity.ok(conversions);
    }
    @GetMapping("/getFirst")
    public long lastConversationId(@RequestParam String username) {
        List<ConversionRespanceDTO> conversionRespanceDTOList = conversionService.getConversionByCreator(username);

        // Vérifier si la liste n'est pas vide avant d'essayer d'accéder au premier élément
        if (!conversionRespanceDTOList.isEmpty()) {
            long id = conversionRespanceDTOList.get(0).getId(); // Utiliser get(0) pour obtenir le premier élément
            return id;
        } else {
            // Gérer le cas où la liste est vide, par exemple, en lançant une exception ou en renvoyant une valeur par défaut
            throw new NoSuchElementException("No conversation found for user: " + username);
        }
    }

    // Récupérer une conversation par ID
    @GetMapping
    public ResponseEntity<ConversionRespanceDTO> getConversationById(@RequestParam long conversationId) {
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

    @GetMapping("/sendTo")
    public  ResponseEntity<List<String>> sendTo(@RequestParam long id,@RequestParam String user){
        List<String> sendToList= conversionService.sendTo(id,user);
        return  ResponseEntity.ok(sendToList);
    }
}
