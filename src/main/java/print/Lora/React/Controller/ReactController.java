package print.Lora.React.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.React.DTO.*;
import print.Lora.React.Model.ReactType;
import print.Lora.React.Service.ReactService;
import java.util.List;

@RestController
@RequestMapping("/api/react")
public class ReactController {

    @Autowired
    private ReactService reactGlobalService;
    @Autowired
    private ReactMapper mapper;


    @GetMapping("/getAll")
    public ResponseEntity<List<ReactRespanceDTO>> getAllReact() {
        List<ReactRespanceDTO> react = reactGlobalService.found();
        return ResponseEntity.ok(react);
    }
    @GetMapping("/getById")
    public ResponseEntity<ReactRespanceDTO> getReactById(@RequestParam long id,
                                                         @RequestParam String type) {
        ReactType typee = mapper.stringToType(type);
        ReactRespanceDTO react = reactGlobalService.foundById(id,typee);
        return ResponseEntity.ok(react);
    }
    @PostMapping("/create")
    public void create(@RequestParam long id,
                       @RequestParam String type) {
        ReactType typee = mapper.stringToType(type);
        System.out.println(typee);
        reactGlobalService.create(id,typee);
    }
    @PostMapping("/like")
    public int likeMessage(@RequestBody LikeRequestDTO likeRequestDTO) {

         return reactGlobalService.LikeMessage(likeRequestDTO);
    }
    @PostMapping("/comment")
    public int comment(@RequestBody CommentRequestDTO likeRequestDTO) {
        System.out.println("like");
        return   reactGlobalService.CommentMessage(likeRequestDTO);
    }
    @GetMapping("/getAllComment")
    public List<CommentRespanceDTO> getAllComment(){
        return  reactGlobalService.findAllComment();
    }


}
