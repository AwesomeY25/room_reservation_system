package app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Email {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // autoincrement
	@Column
	private Long id;
	
	@Column
	private String subject;
	

	@Column
	private String message;


	@Column
	private String email;

	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSent;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDateSent() {
		return dateSent;
	}


	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}


	@Override
	public String toString() {
		return "Email [id=" + id + ", subject=" + subject + ", message=" + message + ", email=" + email + ", dateSent="
				+ dateSent + "]";
	}


	
	
}
