package tec.jvgualdi.emailmicroservice.models;

import jakarta.persistence.*;
import lombok.Data;
import tec.jvgualdi.emailmicroservice.enums.RecipientType;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class MailingRecipient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailing_id", nullable = false)
    private Mailing mailing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="email_address_id", nullable = false)
    private Email emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecipientType recipientType;


}
