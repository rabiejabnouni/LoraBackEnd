package print.Lora.Messanger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Rpository.AppUserRepository;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Messanger.DTO.*;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Repository.ConversionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private ConversionMapper conversionMapper;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository userRepository;  // Pour récupérer les utilisateurs via leurs IDs
    @Autowired
    @Lazy
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;


    // Créer une nouvelle conversation en utilisant les IDs des utilisateurs
    @Override
    public ConversionRespanceDTO createConversation(List<String> createdBy) {
        List<AppUser> users = new ArrayList<>();

        // Récupérer tous les utilisateurs par leurs IDs
        for (String userId : createdBy) {
            Optional<AppUser> userOptional = userRepository.findByEmail(userId);
            userOptional.ifPresent(users::add);  // Ajouter l'utilisateur à la liste s'il existe
        }

        if (users.isEmpty()) {
            throw new RuntimeException("No valid users found");
        }

        // Vérifier si une conversation existe déjà avec cette liste d'utilisateurs
        Optional<ConversionEntity> existingConversation = conversionRepository.findByBetwinUsersContainingAll(users,(long)users.size());
        if (existingConversation.isPresent()) {
            // Retourner la conversation existante si elle existe
            return conversionMapper.entityToRespance(existingConversation.get());
        }

        // Créer une nouvelle conversation si aucune conversation existante n'est trouvée
        ConversionEntity conversation = new ConversionEntity(users);
        conversation.setCreateAt(LocalDateTime.now());
        conversation.setLastmsgAt(LocalDateTime.now());

        return conversionMapper.entityToRespance(conversionRepository.save(conversation));
    }


    // Récupérer toutes les conversations
    @Override
    public List<ConversionRespanceDTO> getAllConversations() {
        List<ConversionEntity> cnvs = conversionRepository.findAll();
        List<ConversionRespanceDTO> list = new ArrayList<>();
        list = cnvs.stream().map(conversionMapper::entityToRespance).collect(Collectors.toList());
        return list;
    }

    // Récupérer une conversation par son ID
    @Override
    public ConversionRespanceDTO getConversationById(long conversationId) {
        ConversionEntity cnv = conversionRepository.findById(conversationId).get();
        ConversionRespanceDTO cnvD = conversionMapper.entityToRespance(cnv);
        return cnvD;

    }
      @Override
      public userRespanceDTO findUser(String username){
        userRespanceDTO user= new userRespanceDTO();
        AppUser user1=appUserService.UserByUsername(username);
        user.setFirstName(user1.getFirstName());
        user.setPathImage(user1.getImageProfilPath());
        user.setLastName(user1.getLastName());

        return user;
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
    public List<ConversionRespanceDTO> getConversionByCreator(String creator) {
        AppUser user = appUserService.UserByUsername(creator);
        List<ConversionEntity> conversion = conversionRepository.findByBetwinUsersContaining(user);

        if (conversion != null && !conversion.isEmpty()) {
            System.out.println("found conversion");
            return conversion.stream()
                    .map(conversionMapper::entityToRespance)
                    .sorted(Comparator.comparing(ConversionRespanceDTO::getLastMsgAt)) // Tri par lastMsgAt
                    .collect(Collectors.toList());
        }

        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<ConversionEntity> conversion=conversionRepository.findById(id);
        List<MessageEntity> messages= conversion.get().getMessages();

        if (messages != null && !messages.isEmpty()) {
            for (MessageEntity msg : messages) {
                try {
                    messageService.delete(msg.getId());
                    System.out.println("Deleted message with ID: " + msg.getId());
                } catch (Exception e) {
                    System.err.println("Failed to delete message with ID: " + msg.getId() + " - " + e.getMessage());
                }
            }
        }

        // Now attempt to delete the conversation
        try {
            conversionRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            System.err.println("Error while deleting conversion: " + e.getMessage());
            throw e; // Optional: throw a custom exception or handle accordingly
        }
    }


    @Override
    public List<String> sendTo(long id, String user){
       ConversionEntity conversion = conversionRepository.findById(id).get();

       List<String> sendTOList = conversion.getBetwinUsers().stream()
               .map(AppUser::getEmail)
               .collect(Collectors.toList());

       // Remove the sender's email
       sendTOList.remove(user);
        return sendTOList;
   }
}
