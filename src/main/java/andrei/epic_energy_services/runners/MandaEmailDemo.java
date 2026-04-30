package andrei.epic_energy_services.runners;

import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.enums.FormaGiuridica;
import andrei.epic_energy_services.services.AppEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class MandaEmailDemo implements CommandLineRunner {

    @Autowired
    private AppEmailService appEmailService;

    @Override
    public void run(String... args) throws Exception {

//        Utente utente = new Utente(
//                "tave8",
//                "andrei98briceag@gmail.com",
//                "1234",
//                "Giuseppe",
//                "Tavella"
//        );
//
//        this.appEmailService.sendWelcome(utente);
        
        // Cliente cliente = new Cliente(
        //         UUID.randomUUID(),
        //         "ragione irrazionale",
        //         "partita di calcetto",
        //         "giuseppetavella8@gmail.com",
        //         "giuseppetavella8@gmail.com",
        //         "3423432423",
        //         BigDecimal.ONE,
        //         FormaGiuridica.PA,
        //         "giuseppetavella8@gmail.com",
        //         "Giuseppe",
        //         "Tavella",
        //         "3342342342342",
        //         LocalDate.now(),
        //         LocalDate.now(),
        //         "s",
        //         List.of()
        // );
        //
        // this.appEmailService.mandaEmailFatturaACliente(cliente);

    }
}
