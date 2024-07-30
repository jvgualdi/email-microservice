package tec.jvgualdi.emailmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.jvgualdi.emailmicroservice.models.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {



}
