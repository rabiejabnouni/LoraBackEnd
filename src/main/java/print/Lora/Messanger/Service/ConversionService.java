package print.Lora.Messanger.Service;


import print.Lora.Messanger.DTO.ConversionRespanceDTO;
import print.Lora.Messanger.DTO.userRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;

import java.util.List;

public interface ConversionService {


    // Créer une nouvelle conversation en utilisant les IDs des utilisateurs
    ConversionRespanceDTO createConversation(List<String> userIds);

    // Récupérer toutes les conversations
    List<ConversionRespanceDTO> getAllConversations();

    // Récupérer une conversation par son ID
    ConversionRespanceDTO getConversationById(long conversationId);

    userRespanceDTO findUser(String username);

    // Mettre à jour l'heure du dernier message
    void Update(ConversionEntity conversion,String context);

    // Ajouter un utilisateur à une conversation existante en utilisant l'ID
    void addUserToConversation(long conversationId, long userId);



    List<ConversionRespanceDTO> getConversionByCreator(String creator);

    void delete(long id);



    List<String> sendTo(long id, String user);
}
