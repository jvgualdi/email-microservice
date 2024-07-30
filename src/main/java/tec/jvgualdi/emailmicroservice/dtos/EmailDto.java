package tec.jvgualdi.emailmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {

    @NotBlank
    private String replyTo;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
//    @NotBlank
//    @Email
//    private String[] cc;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;

}
