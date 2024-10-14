package print.Lora.TimeTable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.TimeTable.DTO.ClassMapper;
import print.Lora.TimeTable.DTO.ClassRequestDTO;
import print.Lora.TimeTable.Entity.ClassEntity;
import print.Lora.TimeTable.Entity.SessionEntity;
import print.Lora.TimeTable.repository.ClassRepository;
import print.Lora.TimeTable.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

public class ClassService {
    @Autowired
    private ClassRepository repository;

    @Autowired
    private ClassMapper mapper;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SessionRepository sessionRepository;



    public void create(ClassRequestDTO requestDTO){
        Optional<ClassEntity> entity = repository.findByResquest(requestDTO.getSpeciality(),
                                                                requestDTO.getLevel(),
                                                                requestDTO.getClassNumber());
        if(entity.isPresent()){
            throw new RuntimeException("class really existe ");

        }
        repository.save(mapper.requestToEntity(requestDTO));
    }

    public void addStudent(String userName,ClassRequestDTO requestDTO){
        Optional<ClassEntity> entity = repository.findByResquest(requestDTO.getSpeciality(),
                requestDTO.getLevel(),
                requestDTO.getClassNumber());
        if(entity.isPresent()){
            AppUser student = appUserService.UserByUsername(userName);
            List<AppUser> students = entity.get().getStudents();
            students.add(student);
        repository.updateStudents(entity.get().getId(),students);
        }
        throw new RuntimeException("class not found");

    }
    public void addSession(long idSession, ClassRequestDTO requestDTO){
        Optional<ClassEntity> entity = repository.findByResquest(requestDTO.getSpeciality(),
                requestDTO.getLevel(),
                requestDTO.getClassNumber());
        if(entity.isPresent()){
            Optional<SessionEntity> sessionEntities = sessionRepository.findById(idSession);
            List<SessionEntity> students = entity.get().getSessions();
            students.add(sessionEntities.get());
            repository.updateSessions(entity.get().getId(),students);
        }
        throw new RuntimeException("class not found");
    }

    public void deleteStudent(long idClass, String userName){
        Optional<ClassEntity> entity = repository.findById(idClass);
        if(entity.isPresent()){
           appUserService.UserByUsername(userName);
           repository.delete(entity.get());
        }
        throw new RuntimeException("class not found");
    }

    public void deleteSession(long idClass, long idSession){
        Optional<ClassEntity> entity = repository.findById(idClass);
        if(entity.isPresent()){
            sessionRepository.findById(idSession);
            repository.delete(entity.get());
        }
        throw new RuntimeException("class not found");
    }

}
