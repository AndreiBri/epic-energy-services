package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.payloads.in_request.RegistrazioneMandataDTO;
import andrei.epic_energy_services.payloads.in_response.RegistrazioneDaMandareDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtentiService utentiService;

    /**
     *  Call this only when the user is trying to login.
     *      * The user can proceed with the login, only if the account 
     *      * associated with the email that they sent, is 
     */
    // public AfterLoginDTO login(LoginSentDTO body) throws NotFoundException {
    //
    //     User userFound;
    //     String accessToken;
    //
    //     try {
    //
    //         userFound = this.usersService.findByEmail(body.email());
    //
    //         // we compare the password coming from the request's body
    //         // with the actual password found in the database
    //         boolean isPasswordMatch = this.bcrypt.matches(body.password(), userFound.getPassword());
    //
    //         // se la password dell'utente corrisponde a quella che si trova
    //         // nell'utente che ha questa email, vuol dire che l'utente si è loggato
    //         // con successo, quindi crea il token
    //         if (isPasswordMatch) {
    //
    //             accessToken = this.tokenTools.generateToken(userFound);
    //
    //         } else {
    //             throw new UnauthorizedException("Wrong credentials.");
    //         }
    //
    //     } catch (NotFoundException ex) {
    //         throw new UnauthorizedException("Wrong credentials.");
    //     }
    //
    //
    //     // user has not verified their email
    //     if(!userFound.isVerifiedEmail()) {
    //
    //         String verificationUrl = this.generateNewEmailVerificationUrl(userFound);
    //         this.appEmailService.sendVerifyEmail(userFound, verificationUrl);
    //
    //         // System.out.println("USER HAS NOT VERIFIED THEIR EMAIL");
    //         throw new EmailVerificationException("User can login only after verifying their email. "
    //                 +"An email has been sent with a new verification link.");
    //     }
    //
    //
    //     return new AfterLoginDTO(accessToken);
    //
    // }


    /**
     * Register/sign up a user.
     */
    public RegistrazioneDaMandareDTO register(RegistrazioneMandataDTO body) {

        // add a user to DB
        Utente newUser = this.utentiService.create(body);
        
        return new RegistrazioneDaMandareDTO(newUser);
    }
    
}
