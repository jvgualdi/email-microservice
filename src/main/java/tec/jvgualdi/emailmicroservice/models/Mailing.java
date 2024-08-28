package tec.jvgualdi.emailmicroservice.models;

import jakarta.persistence.*;
import lombok.Data;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "mailing_information")
public class Mailing implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String replyTo;

    @Column(nullable = false)
    private String emailFrom;

    @OneToMany(mappedBy = "mailing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MailingRecipient> recipients = new HashSet<>();


    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private LocalDateTime emailSentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmail statusEmail;


}
