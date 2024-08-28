package tec.jvgualdi.emailmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tec.jvgualdi.emailmicroservice.models.Mailing;
import java.util.UUID;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, UUID> {

}
