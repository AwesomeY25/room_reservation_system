package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // autoincrement
	@Column
	private Long id;
	
	@Column
	private String className;
	
	@Column
	private String daySchedule;
	
	@Column
	private String timeSchedule;
	
	@Column
	private String buildingName;
	
	@Column
	private String roomNumber;

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getDaySchedule() {
		return daySchedule;
	}


	public void setDaySchedule(String daySchedule) {
		this.daySchedule = daySchedule;
	}


	public String getTimeSchedule() {
		return timeSchedule;
	}


	public void setTimeSchedule(String timeSchedule) {
		this.timeSchedule = timeSchedule;
	}


	public String getBuildinName() {
		return buildingName;
	}


	public void setBuildingName(String building) {
		this.buildingName = building;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	@Override
    public String toString() {
        return "Schedule: " + className + daySchedule + timeSchedule;
    }
}
