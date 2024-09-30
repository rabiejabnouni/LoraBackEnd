package print.Lora.Auth.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import print.Lora.Auth.DTO.LoginRequest;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Model.ConfirmationToken;
import print.Lora.Auth.Rpository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ConfirmationToken login(LoginRequest dto) {
        // Chercher l'utilisateur par email
        Optional<AppUser> user = appUserRepository
                .findByEmail(dto.getUsername());

        // Vérifier si l'utilisateur existe
        if (!user.isPresent()) {
            throw new IllegalStateException("Email non trouvé");
        }

        // Obtenir le mot de passe stocké de l'utilisateur
        String encodedPassword = user.get().getPassword();

        // Vérifier si le mot de passe donné correspond au mot de passe stocké
        boolean passwordMatches = bCryptPasswordEncoder
                .matches(dto.getPassword(), encodedPassword);

        // Si le mot de passe est incorrect, lancer une exception
        if (!passwordMatches) {
            throw new IllegalStateException("Mot de passe incorrect");
        }
        if(!user.get().getEnabled()){
            throw  new IllegalStateException("client no enabled ");
        }

        // Générer un token de confirmation
        String token = UUID.randomUUID().toString();

        // Créer un nouvel objet ConfirmationToken
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(15),
                user.get()
        );

        return confirmationToken; // Retourner le token généré
    }
}
