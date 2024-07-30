package tec.jvgualdi.emailmicroservice.models;

import jakarta.persistence.*;
import lombok.Data;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "email")
public class EmailModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String replyTo;
    private String emailFrom;
    private String emailTo;
//    private String[] to;
//    private String[] cc;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    private LocalDateTime emailSentDate;
    private StatusEmail statusEmail;


}
