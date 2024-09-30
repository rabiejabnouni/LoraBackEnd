package print.Lora.Auth.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordRequestDTO {
    private String email;
    private String newPassword;
}
