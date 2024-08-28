package tec.jvgualdi.emailmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.jvgualdi.emailmicroservice.enums.RecipientType;
import tec.jvgualdi.emailmicroservice.models.MailingRecipient;

import java.util.UUID;

@Repository
public interface MailingRecipientRepository extends JpaRepository<MailingRecipient, UUID> {

}
