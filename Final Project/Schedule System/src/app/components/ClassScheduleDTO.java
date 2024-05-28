package app.components;

public class ClassScheduleDTO {

    private String className;
    private String daySchedule;
    private String timeSchedule;
    private String buildingName;
    private String roomNumber;

    
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



	public String getBuildingName() {
		return buildingName;
	}



	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}



	public String getRoomNumber() {
		return roomNumber;
	}



	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}



	@Override
    public String toString() {
        return "ClassScheduleDTO: " + className + daySchedule + timeSchedule;
    }
}
