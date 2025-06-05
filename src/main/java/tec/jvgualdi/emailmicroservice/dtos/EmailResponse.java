package tec.jvgualdi.emailmicroservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record EmailResponse(@JsonProperty("email_id") UUID emailId, String status) {

    public EmailResponse(UUID emailId, String status) {
        this.emailId = emailId;
        this.status = status;
    }
}
