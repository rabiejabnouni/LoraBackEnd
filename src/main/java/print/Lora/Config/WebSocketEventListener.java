package print.Lora.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import print.Lora.Messanger.DTO.MessageRequestDTO;
import print.Lora.Messanger.DTO.MessageType;
import print.Lora.Messanger.Service.ChatService;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    @Autowired
    private  SimpMessageSendingOperations messageSendingOperations;
    @Autowired
    private ChatService chatService;

    @EventListener
    public void handleWsDisconnectListener( SessionDisconnectEvent event){
        //To listen to another even, create the another method with NewEvent as argument.
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        System.out.println("assba");
        System.out.println(username);
        System.out.println("------------------------------------------------------------------------------------------------------------");

        if(username !=null){
            log.info("User disconnected: {} ", username);
            var message = MessageRequestDTO.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            //pass the message to the broker specific topic : public
            messageSendingOperations.convertAndSend("/topic/public",message);
        }
    }



}