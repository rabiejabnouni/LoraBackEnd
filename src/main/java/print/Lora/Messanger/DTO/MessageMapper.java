package print.Lora.Messanger.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;
import print.Lora.Messanger.Repository.ConversionRepository;
import print.Lora.Messanger.Service.ConversionService;
import print.Lora.Service.ContexteService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageMapper {
    @Autowired
    private ContexteService contexteService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ConversionRepository conversionRepository;
    public MessageRespanceDTO entityToRespance(MessageEntity entity){
        MessageRespanceDTO respanceDTO= new MessageRespanceDTO();
        respanceDTO.setMessage(entity.getMessage());
        respanceDTO.setVuAt(entity.getVuAt());
        respanceDTO.setSenderId(entity.getSender().getEmail());
        respanceDTO.setSendAt(entity.getSendAt());

        return respanceDTO;
    }
    public MessageEntity requestToEntity(MessageRequestDTO requestDTO){
        Optional<ConversionEntity> conversion= conversionRepository.findById(requestDTO.getConversionId());
        if (conversion.isEmpty()){
            throw new RuntimeException("conversion not found");
        }
        System.out.println("conversion found");
        AppUser sender= appUserService.UserByUsername(requestDTO.getSender());
        if (sender==null){
            throw new RuntimeException("sender not found");
        }
        System.out.println("user  is found");
        MessageEntity entity= new MessageEntity();
        entity.setConversion(conversion.get());
        entity.setSender(sender);
        entity.setSendAt(LocalDateTime.now());
        entity.setContext(contexteService.contexte(requestDTO.getMessage()));
        entity.setMessage(requestDTO.getMessage());



        return entity;
    }
}
