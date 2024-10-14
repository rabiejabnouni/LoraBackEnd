package print.Lora.TimeTable.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;
import print.Lora.TimeTable.Entity.SessionEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassRespanceDTO {

    private String speciality;

    private String level;

    private String classNumber;

    private List<AppUser> students;

    private List<SessionEntity> sessions;


}
