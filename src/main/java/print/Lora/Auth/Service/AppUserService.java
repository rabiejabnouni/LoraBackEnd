package print.Lora.Auth.Service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import print.Lora.Auth.Model.AppUser;
import print.Lora.Auth.Model.ConfirmationToken;
import print.Lora.Auth.Rpository.AppUserRepository;
import print.Lora.Auth.Rpository.ConfirmationTokenRepository;
import print.Lora.TimeTable.Entity.ClassEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUser UserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists =  appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);


        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public AppUser findByid(long id){
        Optional<AppUser> user = appUserRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user.get();
    }
    public List<AppUser> getAll(){
        return appUserRepository.findAll();
    }

    public void upDate(String email,boolean isEnable){
        appUserRepository.enableAppUser(email,isEnable);
    }
    public Boolean enableAppUser(String email) {
        return appUserRepository.findByEmail(email).get().getEnabled();
    }
    // Méthode pour valider l'utilisateur et envoyer une notification WebSocket
    public void delete(String email){
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Manually delete confirmation tokens
        confirmationTokenRepository.deleteByAppUserId(user.getId());

        // Now delete the user
        appUserRepository.delete(user);;
    }

    public ClassEntity getClass(String userName){
      Optional<ClassEntity> classEntity=  appUserRepository.findClassByUsername(userName);
        if(classEntity.isEmpty())
            throw new RuntimeException("this user dont have a class");
        return classEntity.get() ;
    }
}
