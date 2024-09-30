package print.Lora.Messanger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Rpository.AppUserRepository;
import print.Lora.Messanger.DTO.MessageRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Repository.ConversionRepository;
import print.Lora.Messanger.Repository.MessageRepository;
import print.Lora.Service.ContexteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private ContexteService contexteService;

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Autowired
    private AppUserRepository userRepository; // Pour récupérer l'utilisateur via son ID

    // Méthode pour envoyer un message en utilisant les IDs
    @Override
    public MessageRespanceDTO sendMessage(long senderId, String messageContent, long conversionId) {
        Optional<AppUser> senderOptional = userRepository.findById(senderId);
        Optional<ConversionEntity> conversionOptional = conversionRepository.findById(conversionId);

        if (senderOptional.isPresent() && conversionOptional.isPresent()) {
            AppUser sender = senderOptional.get();
            ConversionEntity conversion = conversionOptional.get();

            String context = contexteService.contexte(messageContent);
            MessageEntity message = new MessageEntity(sender, messageContent, context, conversion);
            message.setSendAt(LocalDateTime.now());
            conversionService.Update(conversion,context);  // Met à jour l'heure du dernier message de la conversation
            messageRepository.save(message);
            return EntityToRespance(message);

        } else {
            throw new RuntimeException("User or Conversation not found");
        }
    }

    // Méthode pour récupérer tous les messages d'une conversation
    @Override
    public List<MessageRespanceDTO> getMessagesByConversation(long conversionId) {

       List<MessageRespanceDTO> list=new ArrayList<>();
       List<MessageEntity> msgs=messageRepository.findByConversionId(conversionId);

       if(msgs.isEmpty()){
           return list;
       }
       for(MessageEntity mes:msgs){
           list.add(EntityToRespance(mes));
       }

        return list;

    }

    // Méthode pour mettre à jour le statut d'un message comme "vu"
    @Override
    public void markMessageAsSeen(long messageId) {
        Optional<MessageEntity> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isPresent()) {
            MessageEntity message = messageOptional.get();
            message.setVuAt(LocalDateTime.now());
            messageRepository.save(message);
        } else {
            throw new RuntimeException("Message not found");
        }
    }

    @Override
    public MessageRespanceDTO EntityToRespance(MessageEntity msg){
        MessageRespanceDTO message = new MessageRespanceDTO();
        message.setMessage(msg.getMessage());
        message.setSendAt(msg.getSendAt());
        message.setContext(msg.getContext());
        message.setImagePath(msg.getImagePath());
        message.setVuAt(msg.getVuAt());
        message.setSenderId(msg.getSender().getId());
        message.setConversionId(msg.getConversion().getId());
        return message;
    }


}
