package tec.jvgualdi.emailmicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record EmailRequest (

    @NotBlank(message = "Your name cannot be blank")
    @JsonProperty("reply_to")
    String replyTo,
    @NotBlank(message = "Your e-mail cannot be blank")
    @Email(message = "Invalid email format")
    @JsonProperty("email_from")
    String emailFrom,
    @NotEmpty(message = "Recipient email cannot be blank")
    @JsonProperty("email_to")
    List<@Email(message = "Invalid email format")String> emailTo,
    @NotBlank(message = "Subject cannot be blank")
    String subject,
    @NotBlank(message = "Body cannot be blank")
    String body
){
}
