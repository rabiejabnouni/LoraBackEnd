package print.Lora.Messanger.Service;


import print.Lora.Messanger.DTO.MessageRequestDTO;
import print.Lora.Messanger.DTO.MessageRespanceDTO;

import java.util.List;

public interface MessageService {


    // Méthode pour envoyer un message en utilisant les IDs
    void sendMessage(MessageRequestDTO requestDTO);

    // Méthode pour récupérer tous les messages d'une conversation
    List<MessageRespanceDTO> getMessagesByConversation(long conversionId);

    void delete(long id);

    // Méthode pour mettre à jour le statut d'un message comme "vu"
    void markMessageAsSeen(long messageId);


    List<MessageRespanceDTO> getAll();
}
