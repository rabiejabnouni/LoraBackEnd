package print.Lora.TimeTable.Entity;

import jakarta.persistence.Embeddable;
@Embeddable
public class DateEntity {
    private long sessionNumber;
    private Day day;
}
