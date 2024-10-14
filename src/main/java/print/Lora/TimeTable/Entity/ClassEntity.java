package print.Lora.TimeTable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.Lora.Auth.Model.AppUser;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String classNumber;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)  // Correct mapping to UserEntity
    private List<AppUser> students = new ArrayList<>();  // Changed AppUser to UserEntity

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<SessionEntity> sessions;

    public ClassEntity(String speciality, String level, String classNumber, List<AppUser> students, List<SessionEntity> sessions) {
        this.speciality = speciality;
        this.level = level;
        this.classNumber = classNumber;
        this.students = students;
        this.sessions = sessions;
    }

    public ClassEntity(String speciality, String level, String classNumber) {
        this.speciality = speciality;
        this.level = level;
        this.classNumber = classNumber;
    }
}
