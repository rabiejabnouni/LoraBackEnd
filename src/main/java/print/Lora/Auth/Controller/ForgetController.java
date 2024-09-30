package print.Lora.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import print.Lora.Auth.DTO.UpdatePasswordRequestDTO;
import print.Lora.Auth.Model.ConfirmationToken;
import print.Lora.Auth.Service.ForgetService;

@RestController
@RequestMapping(path="api/forget")
public class ForgetController {
    @Autowired
    private ForgetService forgetService;

    @GetMapping("/get")
        public Boolean getIsConfirmed(@RequestParam String token){
        return forgetService.isConfirmed(token);
    }
    @PostMapping
    public String forgetPassword(@RequestParam String username){
        System.out.println(username);
     return forgetService.forget(username);
    }
    @PostMapping("/updatePassword")
    public ConfirmationToken updatePassword(@RequestBody
    UpdatePasswordRequestDTO request){
        if (request.getNewPassword() == null || request.getNewPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        System.out.println(request.getNewPassword());
        System.out.println(request.getEmail());
        return  forgetService.updatePassword(request.getNewPassword(),request.getEmail());

    }


}
