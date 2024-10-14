package print.Lora.TimeTable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.TimeTable.DTO.SessionMapperDTO;
import print.Lora.TimeTable.DTO.SessionRequestDTO;
import print.Lora.TimeTable.DTO.SessionRespanceDTO;
import print.Lora.TimeTable.Entity.ClassEntity;
import print.Lora.TimeTable.Entity.SessionEntity;
import print.Lora.TimeTable.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionMapperDTO sessionMapperDTO;

    @Autowired
    private AppUserService appUserService;
    public List<SessionRespanceDTO> getAll( String userName){
        ClassEntity classEntity=appUserService.getClass(userName);

        List<SessionEntity> sessionEntities=sessionRepository.findAllByUserName(classEntity);

        List<SessionRespanceDTO> sessionRespanceDTOS=sessionEntities
                .stream()
                .map(sessionMapperDTO::EntityToRespance)
                .collect(Collectors.toList());
        return sessionRespanceDTOS;
    }

    public void create(SessionRequestDTO respanceDTO){
        SessionEntity session= sessionMapperDTO.requestToEntity(respanceDTO);
        sessionRepository.save(session);
    }

    public void addPhoto( long idSession, String photo){
        Optional<SessionEntity> session=sessionRepository.findById(idSession);
        if(session.isEmpty()){
            throw new RuntimeException("session not found");
        }
        List<String> photos= session.get().getSharedPhotos();
        photos.add(photo);
        sessionRepository.updatePhoto(idSession,photos);
    }

    public void addFile( long idSession, String file){
        Optional<SessionEntity> session=sessionRepository.findById(idSession);
        if(session.isEmpty()){
            throw new RuntimeException("session not found");
        }
        List<String> photos= session.get().getSharedPhotos();
        photos.add(file);
        sessionRepository.updateFiles(idSession,photos);
    }
    public void addChapter( long idSession, String chapter ){
        Optional<SessionEntity> session=sessionRepository.findById(idSession);
        if(session.isEmpty()){
            throw new RuntimeException("session not found");
        }
        List<String> photos= session.get().getSharedPhotos();
        photos.add(chapter);
        sessionRepository.updateChapter(idSession,photos);
    }
    public void addClassroomCode( long idSession, String code){
        Optional<SessionEntity> session=sessionRepository.findById(idSession);
        if(session.isEmpty()){
            throw new RuntimeException("session not found");
        }
        List<String> photos= session.get().getSharedPhotos();
        photos.add(code);
        sessionRepository.updateClassroomCode(idSession,photos);
    }



}
