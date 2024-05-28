package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // autoincrement
	@Column
	private Long id;
	
	@Column
	private String subject;
	
	@Column
	private String message;
	
	@Column
	private String senderEmail;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @Override
    public String toString() {
        return "EmailRequest [subject=" + subject + ", message=" + message + ", senderEmail=" + senderEmail + "]";
    }
}
