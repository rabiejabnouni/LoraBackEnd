package print.Lora.Messanger.Service;


import print.Lora.Messanger.DTO.ConversionRespanceDTO;
import print.Lora.Messanger.Model.ConversionEntity;

import java.util.List;

public interface ConversionService {


    // Créer une nouvelle conversation en utilisant les IDs des utilisateurs
    ConversionRespanceDTO createConversation(String createdBy, List<Long> userIds);

    // Récupérer toutes les conversations
    List<ConversionRespanceDTO> getAllConversations();

    // Récupérer une conversation par son ID
    ConversionRespanceDTO getConversationById(long conversationId);

    // Mettre à jour l'heure du dernier message
    void Update(ConversionEntity conversion,String context);

    // Ajouter un utilisateur à une conversation existante en utilisant l'ID
    void addUserToConversation(long conversationId, long userId);

    ConversionRespanceDTO EntityToRespanceDto(ConversionEntity cnv);
}
