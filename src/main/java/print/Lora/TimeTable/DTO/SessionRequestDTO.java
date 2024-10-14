package print.Lora.TimeTable.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.TimeTable.Entity.DateEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequestDTO {
    private String matter;
    private String userName;
    private DateEntity date;
    private long salle;
    private String prof;
}
