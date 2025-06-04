package tec.jvgualdi.emailmicroservice.dtos;

import java.util.UUID;

public record EmailResponse(UUID emailId, String status) {

    public EmailResponse(UUID emailId, String status) {
        this.emailId = emailId;
        this.status = status;
    }
}
