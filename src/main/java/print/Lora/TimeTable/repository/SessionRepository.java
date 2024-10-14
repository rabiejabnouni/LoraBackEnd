package print.Lora.TimeTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import print.Lora.TimeTable.Entity.ClassEntity;
import print.Lora.TimeTable.Entity.SessionEntity;

import java.util.List;
@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {


    @Query("SELECT s FROM SessionEntity s WHERE s.classEntity = ?1")
    List<SessionEntity> findAllByUserName(ClassEntity classEntity);


    @Modifying
    @Transactional
    @Query("UPDATE SessionEntity s SET s.sharedPhotos = ?2 WHERE s.id = ?1")
    void updatePhoto(long idSession, List<String> photo);


    @Modifying
    @Transactional
    @Query("UPDATE SessionEntity s SET s.sharedFiles = ?2 WHERE s.id = ?1")
    void updateFiles(long idSession, List<String> files);


    @Modifying
    @Transactional
    @Query("UPDATE SessionEntity s SET s.chapitre = ?2 WHERE s.id = ?1")
    void updateChapter(long idSession, List<String> chapters);


    @Modifying
    @Transactional
    @Query("UPDATE SessionEntity s SET s.classroomCode = ?2 WHERE s.id = ?1")
    void updateClassroomCode(long idSession, List<String> code);
}
