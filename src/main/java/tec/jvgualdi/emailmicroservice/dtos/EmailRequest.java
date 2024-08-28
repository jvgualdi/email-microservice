package tec.jvgualdi.emailmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class EmailRequest {

    @NotBlank(message = "Your name cannot be blank")
    private String replyTo;

    @NotBlank(message = "Your e-mail cannot be blank")
    @Email(message = "Invalid email format")
    private String emailFrom;

    @NotEmpty(message = "Recipient email cannot be blank")
    private List<@Email(message = "Invalid email format")String> emailTo;

    private List<@Email(message = "Invalid email format")String> cc;

    private List<@Email(message = "Invalid email format")String> bcc;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotBlank(message = "Body cannot be blank")
    private String body;


}
