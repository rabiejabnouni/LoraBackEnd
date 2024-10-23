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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageMapper {
    @Autowired
    private ContexteService contexteService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ConversionRepository conversionRepository;
    public MessageRespanceDTO entityToRespance(MessageEntity entity) {
        ConversionEntity conversion = conversionRepository.findById(entity.getConversion().getId()).orElseThrow(() ->
                new RuntimeException("Conversion not found with id: " + entity.getConversion().getId())
        );

        MessageRespanceDTO respanceDTO = new MessageRespanceDTO();

        List<String> sendTOList = conversion.getBetwinUsers().stream()
                .map(AppUser::getEmail)
                .collect(Collectors.toList());

        // Remove the sender's email
        sendTOList.remove(entity.getSender().getEmail());

        respanceDTO.setSendTO(sendTOList);
        respanceDTO.setConversionId(entity.getConversion().getId());
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
