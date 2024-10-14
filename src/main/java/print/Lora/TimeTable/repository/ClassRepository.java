package print.Lora.TimeTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.Auth.Model.AppUser;
import print.Lora.TimeTable.Entity.ClassEntity;
import print.Lora.TimeTable.Entity.SessionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {


    @Query("SELECT C FROM ClassEntity C WHERE C.speciality = ?1 AND C.level = ?2 AND C.classNumber = ?3")
    Optional<ClassEntity> findByResquest(String speciality, String level, String classNumber);

    @Modifying
    @Transactional  // Assurez-vous que cette méthode soit transactionnelle
    @Query("UPDATE ClassEntity c SET c.students = ?2 WHERE c.id = ?1")
    void updateStudents(long idClass, List<AppUser> students);

    @Modifying
    @Transactional
    @Query("UPDATE ClassEntity c SET c.sessions = ?2 WHERE c.id = ?1")
    void updateSessions(long idClass, List<SessionEntity> sessionEntities);
}

