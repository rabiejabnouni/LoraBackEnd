package print.Lora.TimeTable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String matter;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String prof;

    @Column(nullable = false)
    private long salleNumber;

    @Embedded
    private DateEntity date;

    @ManyToOne
    @JoinColumn(name = "classEntity_id")
    private ClassEntity classEntity;

    @ElementCollection
    private List<String> classroomCode = new ArrayList<>();

    @ElementCollection
    private List<String> chapitre = new ArrayList<>();

    @ElementCollection
    private List<String> sharedPhotos = new ArrayList<>();

    @ElementCollection
    private List<String> sharedFiles = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<HomeWorkEntity> homeWorkEntities = new ArrayList<>();

    public SessionEntity(String matter, String prof, long salleNumber, DateEntity date, List<String> classroomCode,
                         List<String> chapitre, List<String> sharedPhotos,
                         List<String> sharedFiles, List<HomeWorkEntity> homeWorkEntities) {
        this.matter = matter;
        this.prof = prof;
        this.salleNumber = salleNumber;
        this.date = date;
        this.classroomCode = classroomCode;
        this.chapitre = chapitre;
        this.sharedPhotos = sharedPhotos;
        this.sharedFiles = sharedFiles;
        this.homeWorkEntities = homeWorkEntities;
    }
}
