package tec.jvgualdi.emailmicroservice.services;


import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailLogRepository emailLogRepository;
    private final JavaMailSender mailSender;


    @Transactional
    public EmailResponse sendEmail(EmailRequest req) {
        var log = new EmailLog();
        log.setId(UUID.randomUUID());
        log.setReplyTo(req.replyTo());
        log.setFrom(req.emailFrom());
        log.setRecipients(Map.of(
                "to",  req.emailTo(),
                "cc",  req.cc()  != null ? req.cc()  : List.of(),
                "bcc", req.bcc() != null ? req.bcc() : List.of()
        ));
        log.setSubject(req.subject());
        log.setBody(req.body());
        log.setStatus(StatusEmail.PENDING);
        log.setSentAt(Instant.now());
        log.setUpdatedAt(Instant.now());
        emailLogRepository.save(log);

        try {
            var msg = new SimpleMailMessage();
            msg.setFrom(log.getFrom());
            msg.setTo(  log.getRecipients().get("to").toArray(new String[0]) );
            msg.setCc(  log.getRecipients().get("cc").toArray(new String[0]) );
            msg.setBcc( log.getRecipients().get("bcc").toArray(new String[0]) );
            msg.setReplyTo(log.getReplyTo());
            msg.setSubject(log.getSubject());
            msg.setText(log.getBody());

            mailSender.send(msg);

            log.setStatus(StatusEmail.SENT);
        } catch (MailException ex) {
            log.setStatus(StatusEmail.ERROR);
        } finally {
            log.setUpdatedAt(Instant.now());
            var email = emailLogRepository.save(log);
            return new EmailResponse(email.getId(), email.getStatus().name());
        }
    }


}
