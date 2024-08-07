package tec.jvgualdi.emailmicroservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tec.jvgualdi.emailmicroservice.dtos.EmailDto;
import tec.jvgualdi.emailmicroservice.models.EmailModel;
import tec.jvgualdi.emailmicroservice.services.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        return new ResponseEntity<>(emailService.sendEmail(emailDto.convertToEmailModel()), HttpStatus.CREATED);
    }

}
