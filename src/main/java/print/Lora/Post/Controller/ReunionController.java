package print.Lora.Post.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Post.Dto.ReunionRequestDto;
import print.Lora.Post.Dto.ReunionResponseDto;
import print.Lora.Post.Service.ReunionService;

import java.util.List;

@RestController
@RequestMapping("/api/reunion")
public class ReunionController {

    @Autowired
    private ReunionService reunionService;

    // Endpoint to create a new Reunion
    @PostMapping("/create")
    public ResponseEntity<ReunionResponseDto> createReunion(@RequestBody ReunionRequestDto requestDto) {
        ReunionResponseDto responseDto = reunionService.createReunion(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Endpoint to update a Reunion
    @PutMapping("/update/{reunionId}")
    public ResponseEntity<ReunionResponseDto> updateReunion(
            @PathVariable Long reunionId, @RequestBody ReunionRequestDto requestDto) {
        ReunionResponseDto updatedReunion = reunionService.updateReunion(reunionId, requestDto);
        return new ResponseEntity<>(updatedReunion, HttpStatus.OK);
    }

    // Endpoint to get a Reunion by ID
    @GetMapping("/{reunionId}")
    public ResponseEntity<ReunionResponseDto> getReunionById(@PathVariable Long reunionId) {
        ReunionResponseDto responseDto = reunionService.getReunionById(reunionId);
        return responseDto != null ?
                new ResponseEntity<>(responseDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to get all Reunions
    @GetMapping("/all")
    public ResponseEntity<List<ReunionResponseDto>> getAllReunions() {
        List<ReunionResponseDto> reunions = reunionService.getAllReunions();
        return new ResponseEntity<>(reunions, HttpStatus.OK);
    }

    // Endpoint to delete a Reunion by ID
    @DeleteMapping("/delete/{reunionId}")
    public ResponseEntity<Void> deleteReunion(@PathVariable Long reunionId) {
        reunionService.deleteReunion(reunionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
