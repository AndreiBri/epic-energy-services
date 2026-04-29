package andrei.epic_energy_services.config;

import com.cloudinary.Cloudinary;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    @Bean
    public Cloudinary getFileUploader(
            @Value("${cloudinary.name}") String cloudName,
            @Value("${cloudinary.apikey}") String apiKey,
            @Value("${cloudinary.secret}") String apiSecret)
    {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }


    /**
     * The instance will be using to send emails.
     */
    @Bean
    public Resend getEmailSender(
            @Value("${resend.apikey}") String resendApiKey)
    {
        return new Resend(resendApiKey);
    }

    /**
     * Default params for sending an email.
     * For example, a default domain, user and name.
     */
    @Bean
    public CreateEmailOptions.Builder getEmailSenderOptions()
    {
        return CreateEmailOptions.builder().from("Epicode - Team 1 <epicode-team1@mail.giuseppetavella.com>");
    }
    
}
