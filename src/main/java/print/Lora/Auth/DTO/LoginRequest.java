package print.Lora.Auth.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
