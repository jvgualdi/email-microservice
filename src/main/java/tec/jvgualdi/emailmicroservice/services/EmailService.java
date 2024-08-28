package tec.jvgualdi.emailmicroservice.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tec.jvgualdi.emailmicroservice.dtos.EmailRequest;
import tec.jvgualdi.emailmicroservice.enums.RecipientType;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;
import tec.jvgualdi.emailmicroservice.models.Email;
import tec.jvgualdi.emailmicroservice.models.Mailing;
import tec.jvgualdi.emailmicroservice.models.MailingRecipient;
import tec.jvgualdi.emailmicroservice.repositories.EmailRepository;
import tec.jvgualdi.emailmicroservice.repositories.MailingRecipientRepository;
import tec.jvgualdi.emailmicroservice.repositories.MailingRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private MailingRepository mailingRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private MailingRecipientRepository mailingRecipientRepository;

    @Autowired
    private JavaMailSender mailSender;


    public Mailing sendEmail(EmailRequest mailingRequest) {
        Mailing mailing = new Mailing();
        MailingRecipient recipient = new MailingRecipient();


        try{
            BeanUtils.copyProperties(mailingRequest, mailing);
            SimpleMailMessage message = new SimpleMailMessage();

            List<Email> emailsToSend = saveEmailAddresses(mailingRequest.getEmailTo());
            List<Email> emailsCc = saveEmailAddresses(mailingRequest.getCc());
            List<Email> emailsBcc = saveEmailAddresses(mailingRequest.getBcc());

            message.setFrom(mailingRequest.getEmailFrom());
            message.setTo(emailsToSend.stream().map(Email::getEmailAddress).toArray(String[]::new));
            message.setCc(emailsCc.stream().map(Email::getEmailAddress).toArray(String[]::new));
            message.setBcc(emailsBcc.stream().map(Email::getEmailAddress).toArray(String[]::new));
            message.setSubject(mailingRequest.getSubject());
            message.setText(mailingRequest.getBody());

            mailing.setStatusEmail(StatusEmail.PENDING);
            mailing.setEmailSentDate(LocalDateTime.now());
            mailingRepository.save(mailing);

            linkEmail(emailsToSend, mailing, RecipientType.TO);
            linkEmail(emailsCc, mailing, RecipientType.CC);
            linkEmail(emailsBcc, mailing, RecipientType.BCC);

            mailSender.send(message);

            mailing.setEmailSentDate(LocalDateTime.now());
            mailing.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            mailing.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return mailingRepository.save(mailing);
        }
    }

    public List<Email> saveEmailAddresses(List<String> emailAddresses) {
        return emailAddresses.stream().map(email -> {
            Email existingEmail = emailRepository.findByEmailAddress(email);
            if (existingEmail == null) {
                Email emailToSave = new Email();
                emailToSave.setEmailAddress(email);
                return emailRepository.save(emailToSave);
            }
            System.out.println();
            return existingEmail;
        }).collect(Collectors.toList());
    }

    public void linkEmail(List<Email> emails, Mailing mailing, RecipientType recipientType) {
        emails.forEach(emailAddress -> {
            MailingRecipient recipient = new MailingRecipient();
            recipient.setEmailAddress(emailAddress);
            recipient.setRecipientType(recipientType);
            recipient.setMailing(mailing);
            mailingRecipientRepository.save(recipient);
        });
    }

}
