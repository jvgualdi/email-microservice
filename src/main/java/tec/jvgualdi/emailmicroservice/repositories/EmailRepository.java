package tec.jvgualdi.emailmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.jvgualdi.emailmicroservice.models.Email;

import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {

    public Email findByEmailAddress(String emailAddress);

}
