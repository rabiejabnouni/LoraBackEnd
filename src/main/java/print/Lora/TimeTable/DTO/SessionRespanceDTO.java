package print.Lora.TimeTable.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.TimeTable.Entity.DateEntity;
import print.Lora.TimeTable.Entity.HomeWorkEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRespanceDTO {
    private long id;
    private String matter;
    private DateEntity date;
    private long salle;
    private String prof;
    private List<String> classroomCode;
    private List<String> photos;
    private List<String>files;
    private  List<HomeWorkEntity> homeWorks;
    private List<String> chapter;
}
