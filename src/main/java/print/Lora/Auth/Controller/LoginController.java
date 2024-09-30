package print.Lora.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Auth.Model.ConfirmationToken;
import print.Lora.Auth.Service.LoginService;
import print.Lora.Auth.DTO.LoginRequest;
import print.Lora.Auth.Service.GoogleTokenValidator;


@RestController
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    private GoogleTokenValidator googleTokenValidator;

    @PostMapping("/google")
    public ResponseEntity<ConfirmationToken> loginWithGoogle(@RequestBody String email) {
        System.out.println(email);
        ConfirmationToken newToken = googleTokenValidator.loginWithApiGoogle(email);
        return ResponseEntity.ok(newToken);
    }
    @PostMapping("/login")
    public ConfirmationToken login(@RequestBody LoginRequest dto){
        return loginService.login(dto);
    }
}
