package tec.jvgualdi.emailmicroservice.models;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import tec.jvgualdi.emailmicroservice.enums.StatusEmail;

@Entity
@Table(name = "email_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailLog {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String replyTo;

    @Column(name = "from_addr")
    private String from;

    // armazena um Map<String,List<String>> com keys "to","cc","bcc"
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, List<String>> recipients;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    private StatusEmail status;

    private Instant sentAt;

    private Instant updatedAt;
}
