package tec.jvgualdi.emailmicroservice.models;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;

@Entity
@Table(name = "email_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmailLog {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String replyTo;

    @Column(name = "from_addr")
    private String from;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "email_recipients", joinColumns = @JoinColumn(name = "email_id"))
    private List<String> recipients;

    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    private StatusEmail status;

    private Instant sentAt;
    private Instant updatedAt;
}
