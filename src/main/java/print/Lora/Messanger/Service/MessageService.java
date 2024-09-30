package print.Lora.Messanger.Service;


import print.Lora.Messanger.DTO.MessageRespanceDTO;
import print.Lora.Messanger.Model.MessageEntity;

import java.util.List;

public interface MessageService {


    // Méthode pour envoyer un message

    // Méthode pour envoyer un message en utilisant les IDs
    MessageRespanceDTO sendMessage(long senderId, String messageContent, long conversionId);

    // Méthode pour récupérer tous les messages d'une conversation
    List<MessageRespanceDTO> getMessagesByConversation(long conversionId);

    // Méthode pour mettre à jour le statut d'un message comme "vu"
    void markMessageAsSeen(long messageId);


    MessageRespanceDTO EntityToRespance(MessageEntity msg);
}
