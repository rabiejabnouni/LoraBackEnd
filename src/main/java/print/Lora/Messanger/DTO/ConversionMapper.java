package print.Lora.Messanger.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Messanger.Model.ConversionEntity;
import print.Lora.Messanger.Model.MessageEntity;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConversionMapper {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AppUserService appUserService;
    public ConversionRespanceDTO entityToRespance(ConversionEntity entity) {
        ConversionRespanceDTO respanceDTO = new ConversionRespanceDTO();
        respanceDTO.setId(entity.getId());
        List<String> firstName = entity.getBetwinUsers().stream().map(AppUser::getFirstName).collect(Collectors.toList());
        List<String> lastName = entity.getBetwinUsers().stream().map(AppUser::getLastName).collect(Collectors.toList());
        List<String> betwinUserName = entity.getBetwinUsers().stream().map(AppUser::getEmail).collect(Collectors.toList());


        respanceDTO.setFamilyName(firstName);
        respanceDTO.setLastName(lastName);
        respanceDTO.setId(entity.getId());
        respanceDTO.setSendTo(betwinUserName);
        respanceDTO.setLastMsgAt(entity.getLastmsgAt());
        respanceDTO.setCreateAt(entity.getCreateAt());

        // Utilisation de size() pour obtenir le dernier message
        List<MessageEntity> messages =  entity.getMessages();
        if (!messages.isEmpty()) { // Vérifiez que la liste n'est pas vide
            respanceDTO.setDescription(messages.get(messages.size() - 1).getMessage());
        } else {
            respanceDTO.setDescription(""); // Ou une autre valeur par défaut
        }

        respanceDTO.setMessageRespanceDTOS(messages.stream()
                .map(messageMapper::entityToRespance)
                .collect(Collectors.toList()));
        return respanceDTO;
    }

}
