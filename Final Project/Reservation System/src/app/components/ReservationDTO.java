package app.components;

import javax.persistence.Column;

public class ReservationDTO {

    private String timeSlot;
	private String classroomCode;
    private String courseCode;
	private Long reservationID;
	private String studentName;
	private String studentContact;
	private String timeslot;
	private String daySchedule;


    public Long getReservationID() {
		return reservationID;
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
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
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
	public String getDaySchedule() {
		return daySchedule;
	}
	public void setDaySchedule(String daySchedule) {
		this.daySchedule = daySchedule;
	}
    
}
