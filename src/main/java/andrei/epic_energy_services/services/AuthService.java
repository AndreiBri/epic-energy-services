package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.exceptions.NotFoundException;
import andrei.epic_energy_services.exceptions.UnauthorizedException;
import andrei.epic_energy_services.payloads.in_request.LoginMandatoDTO;
import andrei.epic_energy_services.payloads.in_request.RegistrazioneMandataDTO;
import andrei.epic_energy_services.payloads.in_response.LoginDaMandareDTO;
import andrei.epic_energy_services.payloads.in_response.RegistrazioneDaMandareDTO;
import andrei.epic_energy_services.security.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private TokenTools tokenTools;

    /**
     *  Fai il login di un utente.
     */
    public LoginDaMandareDTO login(LoginMandatoDTO body) throws NotFoundException {

        Utente userFound;
        String accessToken;

        try {

            userFound = this.utentiService.findByEmail(body.email());

            // we compare the password coming from the request's body
            // with the actual password found in the database
            boolean isPasswordMatch = this.bcrypt.matches(body.password(), userFound.getPassword());

            // se la password dell'utente corrisponde a quella che si trova
            // nell'utente che ha questa email, vuol dire che l'utente si è loggato
            // con successo, quindi crea il token
            if (isPasswordMatch) {

                accessToken = this.tokenTools.generateToken(userFound);

            } else {
                throw new UnauthorizedException("Wrong credentials.");
            }

        } catch (NotFoundException ex) {
            throw new UnauthorizedException("Wrong credentials.");
        }
        

        return new LoginDaMandareDTO(accessToken);

    }


    /**
     * Register/sign up a user.
     */
    public RegistrazioneDaMandareDTO register(RegistrazioneMandataDTO body) {

        // add a user to DB
        Utente newUser = this.utentiService.create(body);
        
        return new RegistrazioneDaMandareDTO(newUser);
    }
    
}
