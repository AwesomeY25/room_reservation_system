package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> 
{

}
