package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.EmailRequest;

@Repository
public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> 
{

}
