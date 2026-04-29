package andrei.epic_energy_services.services;

import andrei.epic_energy_services.exceptions.EmailSendingException;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Send emails.
 * Hides the email API library-specific implementation details.
 */
@Service
public class EmailService {

    @Autowired
    private Resend resend;

    @Autowired
    private CreateEmailOptions.Builder defaultParams;

    /**
     * Send an email with the default sender name, user and domain.
     *
     * @throws EmailSendingException if any problem occurred during email sending
     */
    public String sendEmail(String recipient, String subject, String html) throws EmailSendingException {

        CreateEmailOptions params = this.buildEmailParams(recipient, subject, html);

        try {
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();

        } catch (ResendException e) {
            throw new EmailSendingException(e.getMessage());
        }
    }


    /**
     * Build the email params.
     */
    public CreateEmailOptions buildEmailParams(String recipient, String subject, String html) {
        return this.defaultParams
                .to(recipient)
                .subject(subject)
                .html(html)
                .build();
    }



}