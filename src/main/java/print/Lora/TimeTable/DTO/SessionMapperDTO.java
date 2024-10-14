package print.Lora.TimeTable.DTO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.TimeTable.Entity.ClassEntity;
import print.Lora.TimeTable.Entity.SessionEntity;

@Service
public class SessionMapperDTO {
    @Autowired
    private AppUserService appUserService;
    public SessionEntity requestToEntity(SessionRequestDTO sessionRequestDTO){
        SessionEntity session = new SessionEntity();
        ClassEntity classEntity=appUserService.getClass(sessionRequestDTO.getUserName());
        session.setClassEntity(classEntity);
        session.setUserName(sessionRequestDTO.getUserName());
        session.setMatter(sessionRequestDTO.getMatter());
        session.setProf(sessionRequestDTO.getProf());
        session.setDate(sessionRequestDTO.getDate());
        session.setSalleNumber(sessionRequestDTO.getSalle());
        return session;
    }
    public SessionRespanceDTO EntityToRespance(SessionEntity session){
        SessionRespanceDTO sessionRespanceDTO=new SessionRespanceDTO();
        sessionRespanceDTO.setId(session.getId());
        sessionRespanceDTO.setDate(session.getDate());
        sessionRespanceDTO.setProf(session.getProf());
        sessionRespanceDTO.setMatter(sessionRespanceDTO.getMatter());
        sessionRespanceDTO.setChapter(session.getChapitre());
        sessionRespanceDTO.setPhotos(session.getSharedPhotos());
        sessionRespanceDTO.setSalle(session.getSalleNumber());
        sessionRespanceDTO.setFiles(session.getSharedFiles());
        sessionRespanceDTO.setClassroomCode(session.getClassroomCode());
        sessionRespanceDTO.setHomeWorks(session.getHomeWorkEntities());
        return sessionRespanceDTO;
    }
}