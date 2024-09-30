package print.Lora.Messanger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Rpository.AppUserRepository;
import print.Lora.Messanger.DTO.ConversionRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Repository.ConversionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private AppUserRepository userRepository;  // Pour récupérer les utilisateurs via leurs IDs
    @Autowired
    @Lazy
    private MessageService messageService;

    // Créer une nouvelle conversation en utilisant les IDs des utilisateurs
    @Override
    public ConversionRespanceDTO createConversation(String createdBy, List<Long> userIds) {
        List<AppUser> users = new ArrayList<>();

        // Récupérer tous les utilisateurs par leurs IDs
        for (Long userId : userIds) {
            Optional<AppUser> userOptional = userRepository.findById(userId);
            userOptional.ifPresent(users::add);  // Ajouter l'utilisateur à la liste s'il existe
        }

        if (users.isEmpty()) {
            throw new RuntimeException("No valid users found");
        }

        ConversionEntity conversation = new ConversionEntity(createdBy);
        conversation.setBetwinUsers(users);
        conversation.setCreateAt(LocalDateTime.now());
        conversation.setLastmsgAt(LocalDateTime.now());
        return EntityToRespanceDto(conversionRepository.save(conversation));
    }

    // Récupérer toutes les conversations
    @Override
    public List<ConversionRespanceDTO> getAllConversations() {
        List<ConversionEntity> cnvs = conversionRepository.findAll();
        List<ConversionRespanceDTO> list = new ArrayList<>();
        for (ConversionEntity cnv : cnvs) {
            list.add(EntityToRespanceDto(cnv));
        }
        return list;
    }

    // Récupérer une conversation par son ID
    @Override
    public ConversionRespanceDTO getConversationById(long conversationId) {
        ConversionEntity cnv = conversionRepository.findById(conversationId).get();
        ConversionRespanceDTO cnvD = EntityToRespanceDto(cnv);
        return cnvD;

    }

    // Mettre à jour l'heure du dernier message
    @Override
    public void Update(ConversionEntity conversion,String context) {
        conversion.setDescription(context);
        conversion.setLastmsgAt(LocalDateTime.now());
        conversionRepository.save(conversion);
    }

    // Ajouter un utilisateur à une conversation existante en utilisant l'ID
    @Override
    public void addUserToConversation(long conversationId, long userId) {
        Optional<ConversionEntity> conversionOptional = conversionRepository.findById(conversationId);
        Optional<AppUser> userOptional = userRepository.findById(userId);

        if (conversionOptional.isPresent() && userOptional.isPresent()) {
            ConversionEntity conversation = conversionOptional.get();
            AppUser user = userOptional.get();
            conversation.getBetwinUsers().add(user);
            conversionRepository.save(conversation);
        } else {
            throw new RuntimeException("User or Conversation not found");
        }
    }

    @Override
    public ConversionRespanceDTO EntityToRespanceDto(ConversionEntity cnv) {
        ConversionRespanceDTO cnvD = new ConversionRespanceDTO();
        cnvD.setCreateAt(cnv.getCreateAt());
        for (MessageEntity msg : cnv.getMessages()) {
            cnvD.getMessageRespanceDTOS().add(messageService.EntityToRespance(msg));
        }
        cnvD.setDescription(cnv.getDescription());
        cnvD.setLastMsgAt(cnv.getLastmsgAt());
        return cnvD;
    }
}
