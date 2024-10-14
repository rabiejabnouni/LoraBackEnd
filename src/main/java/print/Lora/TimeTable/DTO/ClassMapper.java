package print.Lora.TimeTable.DTO;

import org.springframework.stereotype.Service;
import print.Lora.TimeTable.Entity.ClassEntity;

@Service
public class ClassMapper {

    public ClassRespanceDTO entityToRespance(ClassEntity entity){
        ClassRespanceDTO respanceDTO = new ClassRespanceDTO();
        respanceDTO.setSpeciality(entity.getSpeciality());
        respanceDTO.setLevel(entity.getLevel());
        respanceDTO.setClassNumber(entity.getClassNumber());
        return respanceDTO;
    }

     public ClassEntity requestToEntity(ClassRequestDTO requestDTO){
        ClassEntity entity = new ClassEntity(
                requestDTO.getSpeciality(),
                requestDTO.getLevel(),
                requestDTO.getClassNumber()
        );
        return entity;
     }
}
