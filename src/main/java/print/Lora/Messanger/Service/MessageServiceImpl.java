package print.Lora.Messanger.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Rpository.AppUserRepository;
import print.Lora.Messanger.DTO.MessageMapper;
import print.Lora.Messanger.DTO.MessageRequestDTO;
import print.Lora.Messanger.DTO.MessageRespanceDTO;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Repository.ConversionRepository;
import print.Lora.Messanger.Repository.MessageRepository;
import print.Lora.Service.ContexteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void sendMessage(MessageRequestDTO requestDTO) {
        System.out.println(requestDTO);
       MessageEntity message= messageMapper.requestToEntity(requestDTO);
       messageRepository.save(message);
    }

    // Méthode pour récupérer tous les messages d'une conversation
    @Override
    public List<MessageRespanceDTO> getMessagesByConversation(long conversionId) {
   List<MessageEntity> messages=messageRepository.findByConversionId(conversionId);
   return messages.stream().map(messageMapper::entityToRespance).collect(Collectors.toList());
    }
     @Override
     public  void delete(long id){
        messageRepository.deleteById(id);
    }
    // Méthode pour mettre à jour le statut d'un message comme "vu"
    @Override
    public void markMessageAsSeen(long messageId) {
          messageRepository.update(messageId,LocalDateTime.now());
    }
   @Override
    public List<MessageRespanceDTO> getAll(){
       List<MessageEntity> collect = messageRepository.findAll();
      return collect.stream().map(messageMapper::entityToRespance).collect(Collectors.toList());

   }



}
