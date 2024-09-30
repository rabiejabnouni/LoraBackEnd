package print.Lora.Post.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Post.Dto.PauseMusicaleRequestDto;
import print.Lora.Post.Dto.PauseMusicaleRespanceDTO;
import print.Lora.Post.Entity.PauseMusicale;
import print.Lora.Post.Service.PauseMusicaleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pause-musicale")
public class PauseMusicaleController {

    @Autowired
    private PauseMusicaleService pauseMusicaleService;

    // Endpoint to create a new PauseMusicale
    @PostMapping
    public ResponseEntity<PauseMusicaleRespanceDTO> createPauseMusicale(@RequestBody PauseMusicaleRequestDto requestDto) {
        PauseMusicale pauseMusicale = pauseMusicaleService.createPauseMusicale(requestDto);
        PauseMusicaleRespanceDTO responseDto = pauseMusicaleService.convertToDto(pauseMusicale);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Endpoint to get all PauseMusicales
    @GetMapping("/all")
    public ResponseEntity<List<PauseMusicale>> getAllPauseMusicales() {
        List<PauseMusicale> pauseMusicales = pauseMusicaleService.getAllPauseMusicales();
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
    @PutMapping("/update/{id}")
    public ResponseEntity<PauseMusicaleRespanceDTO> updatePauseMusicale(
            @PathVariable Long id, @RequestBody PauseMusicaleRequestDto requestDto) {
        PauseMusicale updatedPauseMusicale = pauseMusicaleService.updatePauseMusicale(id, requestDto);
        PauseMusicaleRespanceDTO responseDto = pauseMusicaleService.convertToDto(updatedPauseMusicale);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Endpoint to delete a PauseMusicale by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePauseMusicale(@PathVariable Long id) {
        pauseMusicaleService.deletePauseMusicale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

