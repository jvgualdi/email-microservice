package tec.jvgualdi.emailmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record EmailRequest (

    @NotBlank(message = "Your name cannot be blank")
    String replyTo,
    @NotBlank(message = "Your e-mail cannot be blank")
    @Email(message = "Invalid email format")
    String emailFrom,
    @NotEmpty(message = "Recipient email cannot be blank")
    List<@Email(message = "Invalid email format")String> emailTo,
    List<@Email(message = "Invalid email format")String> cc,
    List<@Email(message = "Invalid email format")String> bcc,
    @NotBlank(message = "Subject cannot be blank")
    String subject,
    @NotBlank(message = "Body cannot be blank")
    String body
){
}
