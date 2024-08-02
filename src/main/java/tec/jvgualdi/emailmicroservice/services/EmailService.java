package tec.jvgualdi.emailmicroservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;
import tec.jvgualdi.emailmicroservice.models.EmailModel;
import tec.jvgualdi.emailmicroservice.repositories.EmailRepository;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;


    public EmailModel sendEmail(EmailModel email) {
        email.setEmailSentDate(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            mailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(email);
        }
    }

}
