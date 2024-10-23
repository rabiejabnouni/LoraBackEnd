package print.Lora.Post.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import print.Lora.Post.Dto.PauseMusicaleRequestDto;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Service.PauseMusicaleService;
import print.Lora.notifications.DTO.Entity.JournalEntity;
import print.Lora.notifications.DTO.Service.JournalService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pause-musicale")
public class PauseMusicaleController {

    @Autowired
    private PauseMusicaleService pauseMusicaleService;

    @Autowired
    private JournalService journalService;

    // Endpoint to create a new PauseMusicale


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PauseMusicaleRespanceDTO> Create(
            @RequestBody PauseMusicaleRequestDto requestDto) throws IOException {
        PauseMusicale pauseMusicale = pauseMusicaleService.createPauseMusicale(requestDto);
        PauseMusicaleRespanceDTO responseDto = pauseMusicaleService.convertToDto(pauseMusicale);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PostMapping("/save")
    public  void saveImage(@RequestParam("image") MultipartFile image){
        System.out.println("image saved");
    }
    // Endpoint to get all PauseMusicales
    @GetMapping("/all")
    public ResponseEntity<List<PauseMusicaleRespanceDTO>> getAllPauseMusicales() {
        List<PauseMusicaleRespanceDTO> pauseMusicales = pauseMusicaleService.getAllPauseMusicales();
        return new ResponseEntity<>(pauseMusicales, HttpStatus.OK);
    }

    // Endpoint to get a single PauseMusicale by ID
    @GetMapping("/{id}")
    public ResponseEntity<PauseMusicaleRespanceDTO> getPauseMusicaleById(@PathVariable Long id) {
        Optional<PauseMusicale> pauseMusicaleOpt = pauseMusicaleService.getPauseMusicaleById(id);
        if (pauseMusicaleOpt.isPresent()) {
            PauseMusicaleRespanceDTO responseDto = pauseMusicaleService.convertToDto(pauseMusicaleOpt.get());
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update an existing PauseMusicale
    @PutMapping("/played")
    public void played(@RequestParam long id){
        pauseMusicaleService.played(id,true);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PauseMusicaleRespanceDTO> updatePauseMusicale(
            @PathVariable Long id, @RequestBody PauseMusicaleRequestDto requestDto) throws IOException {
        PauseMusicale updatedPauseMusicale = pauseMusicaleService.updatePauseMusicale(id, requestDto);
        PauseMusicaleRespanceDTO responseDto = pauseMusicaleService.convertToDto(updatedPauseMusicale);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Endpoint to delete a PauseMusicale by ID
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePauseMusicale(@RequestParam Long id) {
        pauseMusicaleService.deletePauseMusicale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/journal")
    public List<Map<Long, List<PauseMusicaleRespanceDTO>> >journal(){

        return journalService.journalOfBreak();
    }
}

