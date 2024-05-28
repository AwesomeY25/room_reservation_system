package app.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Email;
import app.repositories.EmailRepository;

import app.entities.EmailRequest;

@Component
public class NotifierAdminComponent {

    @Autowired
    private EmailRepository mRepo;

    public String send(String subject, String msg, String email) {

        Email m = new Email();
        m.setSubject(subject);
        m.setMessage(msg);
        m.setEmail(email);
        m.setDateSent(new Date());

        m = mRepo.save(m);

        return "Sent email " + m;
    }

    public String receive(EmailRequest emailRequest) {
        Email receivedEmail = new Email();
        receivedEmail.setSubject(emailRequest.getSubject());
        receivedEmail.setMessage(emailRequest.getMessage());
        receivedEmail.setEmail(emailRequest.getSenderEmail());
        receivedEmail.setDateSent(new Date());

        receivedEmail = mRepo.save(receivedEmail);

        return "Received email " + receivedEmail;
    }
}
