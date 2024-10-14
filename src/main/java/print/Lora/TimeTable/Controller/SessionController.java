package print.Lora.TimeTable.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.TimeTable.DTO.SessionRequestDTO;
import print.Lora.TimeTable.DTO.SessionRespanceDTO;
import print.Lora.TimeTable.Service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // Get all sessions for a given userName
    @GetMapping("/{userName}")
    public ResponseEntity<List<SessionRespanceDTO>> getAllSessions(@PathVariable String userName) {
        List<SessionRespanceDTO> sessions = sessionService.getAll(userName);
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createSession(@RequestBody SessionRequestDTO sessionRequestDTO) {
        sessionService.create(sessionRequestDTO);
        return ResponseEntity.ok("Session created successfully!");
    }

    // Add a photo to an existing session
    @PutMapping("/{idSession}/addPhoto")
    public ResponseEntity<String> addPhoto(@PathVariable long idSession, @RequestBody String photo) {
        sessionService.addPhoto(idSession, photo);
        return ResponseEntity.ok("Photo added successfully!");
    }

    // Add a file to an existing session
    @PutMapping("/{idSession}/addFile")
    public ResponseEntity<String> addFile(@PathVariable long idSession, @RequestBody String file) {
        sessionService.addFile(idSession, file);
        return ResponseEntity.ok("File added successfully!");
    }

    // Add a chapter to an existing session
    @PutMapping("/{idSession}/addChapter")
    public ResponseEntity<String> addChapter(@PathVariable long idSession, @RequestBody String chapter) {
        sessionService.addChapter(idSession, chapter);
        return ResponseEntity.ok("Chapter added successfully!");
    }

    // Add a classroom code to an existing session
    @PutMapping("/{idSession}/addClassroomCode")
    public ResponseEntity<String> addClassroomCode(@PathVariable long idSession, @RequestBody String code) {
        sessionService.addClassroomCode(idSession, code);
        return ResponseEntity.ok("Classroom code added successfully!");
    }
}
