package print.Lora.Messanger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import print.Lora.Messanger.DTO.MessageRequestDTO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> userSessions = new ConcurrentHashMap<>();

    @Autowired
    private MessageService messageService;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void registerUser(String username, String sessionId) {
        userSessions.put(username, sessionId);
    }

    public void sendMessageToUser(MessageRequestDTO message,List<String> users) {

        for (String user : users) {
            String sessionId = userSessions.get(user);
            if (sessionId != null) {
                messagingTemplate.convertAndSendToUser(sessionId, "/queue/reply", message);
                messageService.sendMessage(message);
                System.out.println("message send :"+sessionId);
            }else {
                messageService.sendMessage(message);
                System.out.println("massage is save "+message);
            }
        }
    }

}

