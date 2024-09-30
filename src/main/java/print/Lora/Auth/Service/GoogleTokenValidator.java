package print.Lora.Auth.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Model.ConfirmationToken;
import print.Lora.Auth.Rpository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GoogleTokenValidator {

    @Value("${google.client-id}")
    private String googleClientId;

    @Autowired
    private AppUserRepository appUserRepository;

    public ConfirmationToken loginWithApiGoogle(String email) {
        System.out.println(email);
        AppUser user = appUserRepository.findByEmail(email).get();
        if (user == null) {
            throw new RuntimeException("you not have a compte");
        }
        String newToken = UUID.randomUUID().toString();

        // Créer un nouvel objet ConfirmationToken
        ConfirmationToken confirmationToken = new ConfirmationToken(
                newToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(15),
                user
        );


        return confirmationToken ;
    }
}
