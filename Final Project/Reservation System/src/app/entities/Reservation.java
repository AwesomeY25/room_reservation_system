package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // autoincrement
    @Column
    private Long reservationID;

    @Column
    private String studentName;

    @Column
    private String studentContact;

    @Column
    private String classroomCode;

    @Column
    private String courseCode;

    @Column
    private String timeslot;

    @Column
    private String daySchedule;

    @Column
    private String status; // New field for reservation status

    public Long getReservationID() {
        return reservationID;
    }

    public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public String getDaySchedule() {
		return daySchedule;
	}

	public void setDaySchedule(String daySchedule) {
		this.daySchedule = daySchedule;
	}

	public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
