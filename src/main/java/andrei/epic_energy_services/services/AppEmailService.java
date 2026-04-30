package andrei.epic_energy_services.services;

import andrei.epic_energy_services.entities.Cliente;
import andrei.epic_energy_services.entities.Utente;
import andrei.epic_energy_services.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Send business-specific emails.
 *
 * Examples:
 * - welcome on signup
 * - reset password
 * - etc.
 */
@Service
public class AppEmailService extends EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Send welcome email on signup.
     */
    public void sendWelcome(Utente utente) {
    Context context = new Context();
        context.setVariable("firstname", utente.getNome());

        String htmlBody = templateEngine.process("emails/signup", context);

        this.sendEmail(utente.getEmail(), "Welcome to Epic Energy Services", htmlBody);
    }


    /**
     * Manda fattura.
     */
    public void mandaEmailFatturaACliente(Cliente cliente) {
        Context context = new Context();
        context.setVariable("firstname", cliente.getNomeContatto());

        String htmlBody = templateEngine.process("emails/fattura", context);

        this.sendEmail(cliente.getEmail(), "You received a new invoice", htmlBody);
    }
    
    
    
    

}