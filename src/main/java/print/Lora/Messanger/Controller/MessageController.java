package print.Lora.Messanger.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import print.Lora.Messanger.DTO.MessageRequestDTO;
import print.Lora.Messanger.DTO.MessageRespanceDTO;

import print.Lora.Messanger.DTO.MessageWebSocketDTO;
import print.Lora.Messanger.DTO.RegisterUserDTO;
import print.Lora.Messanger.Service.ChatService;
import print.Lora.Messanger.Service.MessageService;

import java.util.List;


@Controller // Indique que ce contrôleur traite les requêtes HTTP
@RequestMapping("/api/messages") // Spécifie la racine pour les requêtes liées aux messages
public class MessageController {

    @Autowired // Injection automatique de la dépendance MessageService
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    // Envoi d'un message

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @MessageMapping("/chat.register")
    public void registerUser(SimpMessageHeaderAccessor headerAccessor,@Payload RegisterUserDTO username) {
        logger.debug("Attempting to register user...");
        // Récupérer l'ID de session et le nom d'utilisateur
        String sessionId = headerAccessor.getSessionId();

        logger.debug("Session ID: {}", sessionId);
        logger.debug("Username: {}", username);
        // Logique d'enregistrement...
        chatService.registerUser(username.getUsername(),sessionId);
    }
    @MessageMapping("/chat.sendMessage") // Indique que cette méthode gère les messages WebSocket
    @SendTo("/topic/public") // Envoie le message à tous les abonnés du topic "/topic/public"
    public ResponseEntity<String> sendMessage(@Payload MessageWebSocketDTO requestDTO) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(requestDTO);
        System.out.println("------------------------------------------------------------------------------------------------------------");
        chatService.sendMessageToUser(requestDTO.getMessageRequestDTO(),requestDTO.getSendToList());
        return ResponseEntity.ok(requestDTO.getMessageRequestDTO().getMessage());
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ResponseEntity<MessageRequestDTO> AddUser(@Payload MessageRequestDTO requestDTO,
                                                     SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", requestDTO.getSender());

        return ResponseEntity.ok(requestDTO);
    }

    // Suppression d'un message
    @DeleteMapping // Indique que cette méthode répond aux requêtes DELETE
    public void delete(@RequestParam long id) {
        messageService.delete(id); // Appelle le service pour supprimer le message par son identifiant
    }

    // Récupération des messages d'une conversation spécifique
    @GetMapping("/conversation")
    public ResponseEntity<List<MessageRespanceDTO>> getMessagesByConversation(@RequestParam long conversionId) {
        System.out.println(conversionId);
        List<MessageRespanceDTO> messages = messageService.getMessagesByConversation(conversionId); // Récupère les messages
        return ResponseEntity.ok(messages); // Retourne les messages de la conversation en réponse
    }

    // Marquer un message comme vu
    @PutMapping("/seen") // Indique que cette méthode répond aux requêtes PUT
    public ResponseEntity<String> markMessageAsSeen(@RequestParam long messageId) {
        messageService.markMessageAsSeen(messageId); // Appelle le service pour marquer le message comme vu
        return ResponseEntity.ok("Message marked as seen"); // Retourne une confirmation en réponse
    }

    // Récupération de tous les messages
    @GetMapping // Indique que cette méthode répond aux requêtes GET
    public List<MessageRespanceDTO> getAll() {
        return messageService.getAll(); // Retourne la liste de tous les messages
    }
}
