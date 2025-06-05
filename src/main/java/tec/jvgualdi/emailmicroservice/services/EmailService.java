package tec.jvgualdi.emailmicroservice.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tec.jvgualdi.emailmicroservice.dtos.EmailRequest;
import tec.jvgualdi.emailmicroservice.dtos.EmailResponse;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;
import tec.jvgualdi.emailmicroservice.models.EmailLog;
import tec.jvgualdi.emailmicroservice.repositories.EmailLogRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.password}")
    private String emailPassword;

    private final EmailLogRepository emailLogRepository;
    private final JavaMailSender mailSender;


    @Transactional
    public EmailResponse sendEmail(EmailRequest req) {
        var log = new EmailLog();
        log.setId(UUID.randomUUID());
        log.setReplyTo(req.replyTo());
        log.setFrom(req.emailFrom());
        log.setRecipients(req.emailTo());
        log.setSubject(req.subject());
        log.setBody(req.body());
        log.setStatus(StatusEmail.PENDING);
        log.setSentAt(Instant.now());
        log.setUpdatedAt(Instant.now());
        emailLogRepository.save(log);

        try {
            var msg = new SimpleMailMessage();
            msg.setFrom(log.getFrom());
            msg.setTo( log.getRecipients().toArray(new String[0]) );
            msg.setSubject(log.getSubject());
            msg.setText(log.getBody());
            System.out.println(emailFrom);
            System.out.println(emailPassword);
            mailSender.send(msg);

            log.setStatus(StatusEmail.SENT);
        } catch (MailException ex) {
            log.setStatus(StatusEmail.ERROR);
        } finally {
            log.setUpdatedAt(Instant.now());
            emailLogRepository.save(log);
        }
        return new EmailResponse(log.getId(), log.getStatus().name());
    }


}
