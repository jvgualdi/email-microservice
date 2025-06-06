package tec.jvgualdi.emailmicroservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tec.jvgualdi.emailmicroservice.dtos.EmailRequest;
import tec.jvgualdi.emailmicroservice.dtos.EmailResponse;
import tec.jvgualdi.emailmicroservice.services.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody @Valid EmailRequest emailRequest) {
        var emailResponse = emailService.sendEmail(emailRequest);
        return new ResponseEntity<>(emailResponse, HttpStatus.CREATED);
    }

}
