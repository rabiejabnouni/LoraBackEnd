package print.Lora.Messanger.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Messanger.Model.ConversionEntity;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConversionMapper {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AppUserService appUserService;
    public ConversionRespanceDTO entityToRespance(ConversionEntity entity){
        ConversionRespanceDTO respanceDTO = new ConversionRespanceDTO();
        List<String> firstName = entity.getBetwinUsers().stream().map(AppUser::getFirstName).collect(Collectors.toList());
        List<String> lastName = entity.getBetwinUsers().stream().map(AppUser::getLastName).collect(Collectors.toList());
        List<String> photos = entity.getBetwinUsers().stream().map(AppUser::getImageProfilPath).collect(Collectors.toList());
        int numberOfElementsToAccess = Math.min(firstName.size(), 3);
        List<String> availableFirstName = firstName.subList(0, numberOfElementsToAccess);
        List<String> availableLastName =lastName.subList(0, numberOfElementsToAccess);
        List<String> availablePhoto= photos.subList(0,numberOfElementsToAccess);

        respanceDTO.setFamilyName(availableFirstName);
        respanceDTO.setLastName(availableLastName);
        respanceDTO.setOtherPhoto(availablePhoto);


        respanceDTO.setId(entity.getId());
        System.out.println(respanceDTO.getId());
        respanceDTO.setLastMsgAt(entity.getLastmsgAt());
        respanceDTO.setCreateAt(entity.getCreateAt());
        respanceDTO.setDescription(entity.getDescription());
        respanceDTO.setMessageRespanceDTOS(entity.getMessages().stream()
                .map(messageMapper::entityToRespance)
                .collect(Collectors.toList()));
        return respanceDTO;
    }
}
