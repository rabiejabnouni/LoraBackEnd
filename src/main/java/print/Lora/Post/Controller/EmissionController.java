package print.Lora.Post.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Post.Dto.EmissionRequestDto;
import print.Lora.Post.Dto.EmissionRespanceDto;
import print.Lora.Post.Entity.Emission;
import print.Lora.Post.Service.EmissionService;

import java.util.List;

@RestController
@RequestMapping("/api/emission")
public class EmissionController {

    @Autowired
    private EmissionService emissionService;

    // Endpoint to create a new Emission
    @PostMapping("/create")
    public ResponseEntity<EmissionRespanceDto> createEmission(@RequestBody EmissionRequestDto emissionRequestDto) {
        EmissionRespanceDto responseDto = emissionService.createEmission(emissionRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Endpoint to get an Emission by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmissionRespanceDto> getEmissionById(@PathVariable Long id) {
        EmissionRespanceDto responseDto = emissionService.getEmissionById(id);
        return responseDto != null ?
                new ResponseEntity<>(responseDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to get all Emissions
    @GetMapping("/all")
    public ResponseEntity<List<EmissionRespanceDto>> getAllEmissions() {
        List<EmissionRespanceDto> emissions = emissionService.getAllEmissions();
        return new ResponseEntity<>(emissions, HttpStatus.OK);
    }

    // Endpoint to update an Emission
    @PutMapping("/update/{id}")
    public ResponseEntity<EmissionRespanceDto> updateEmission(
            @PathVariable Long id, @RequestBody EmissionRequestDto emissionRequestDto) {
        EmissionRespanceDto updatedEmission = emissionService.updateEmission(id, emissionRequestDto);
        return new ResponseEntity<>(updatedEmission, HttpStatus.OK);
    }

    // Endpoint to delete an Emission by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmission(@PathVariable Long id) {
        emissionService.deleteEmission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

