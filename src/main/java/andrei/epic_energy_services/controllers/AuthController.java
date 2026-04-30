package andrei.epic_energy_services.controllers;

import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.exceptions.PayloadValidationException;
import andrei.epic_energy_services.payloads.in_request.LoginMandatoDTO;
import andrei.epic_energy_services.payloads.in_request.RegistrazioneMandataDTO;
import andrei.epic_energy_services.payloads.in_response.LoginDaMandareDTO;
import andrei.epic_energy_services.payloads.in_response.RegistrazioneDaMandareDTO;
import andrei.epic_energy_services.services.AppEmailService;
import andrei.epic_energy_services.services.AuthService;
import andrei.epic_energy_services.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private AppEmailService appEmailService;

    /**
     * Login a user.
     */
    @PostMapping("/login")
    public LoginDaMandareDTO login(@RequestBody @Validated LoginMandatoDTO body) {
        return this.authService.login(body);
    }


    /**
     * Sign up a user.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrazioneDaMandareDTO register(@RequestBody @Validated RegistrazioneMandataDTO body,
                                              BindingResult validation) {


        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }

        Utente utenteRegistratoFromDB = this.authService.register(body);

        this.appEmailService.sendWelcome(utenteRegistratoFromDB);

        return new RegistrazioneDaMandareDTO(utenteRegistratoFromDB);
    }


}
