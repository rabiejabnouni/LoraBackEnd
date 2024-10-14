package print.Lora.Auth.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import print.Lora.Auth.DTO.RegistrationRequest;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Service.AppUserService;
import print.Lora.Auth.Service.RegistrationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService
            registrationService;

    private final AppUserService appUserService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Validated RegistrationRequest request) {
        String token = registrationService.register(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signupWithGoogle")
    public ResponseEntity<Map<String, String>> registerWithGoogle(@RequestBody @Validated RegistrationRequest request) {
        String token = registrationService.registerWithGoogle(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    @GetMapping( "/confirm")
    public String confirm(@RequestParam("token") String token) {
        System.out.println("vlaid");
        return registrationService.confirmToken(token);
    }
    @GetMapping( "/users")
    public List<AppUser> getusers() {
        return appUserService.getAll();
    }

    @GetMapping("/enable")
    public boolean enable(@RequestParam String email){
        System.out.println(email);
        return  appUserService.enableAppUser(email);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String email){
        appUserService.delete(email);
    }

}
