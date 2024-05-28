package app.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Schedule;
import app.entities.Room;
import app.repositories.RoomRepository;
import app.repositories.ScheduleRepository;

@Component
public class ClassScheduleComponent {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<String> getAllClassNames() {
        List<Schedule> allSchedules = scheduleRepository.findAll();
        return allSchedules.stream()
                .map(Schedule::getClassName)
                .collect(Collectors.toList());
    }

    public List<String> getAllTakenRoomNumbers() {
        List<Schedule> allSchedules = scheduleRepository.findAll();
        return allSchedules.stream()
                .map(Schedule::getRoomNumber)
                .collect(Collectors.toList());
    }

    public String getAllRoomNumbersNotInConflict(String roomTaken, String daySchedule, String timeSchedule) {
        // Fetch all schedules from the repository
        List<Schedule> allSchedules = scheduleRepository.findAll();

        // Fetch all rooms from the repository
        List<Room> allRooms = roomRepository.findAll();

        // Filter out room numbers in conflict with the provided schedule
        List<String> roomNumbersInConflict = allSchedules.stream()
                .filter(existingSchedule -> isScheduleConflict(existingSchedule, roomTaken, daySchedule, timeSchedule))
                .map(Schedule::getRoomNumber)
                .collect(Collectors.toList());

        // Filter out the room numbers in conflict from the list of all rooms
        List<String> allRoomNumbers = allRooms.stream()
                .map(Room::getRoomNumber)
                .filter(roomNumber -> !roomNumbersInConflict.contains(roomNumber))
                .collect(Collectors.toList());

        return ("All Rooms: " + allRoomNumbers);
    }

    private boolean isScheduleConflict(Schedule existingSchedule, String roomTaken, String daySchedule, String timeSchedule) {
        // Check if either the room number is the same, or both time and day schedules are the same
        return existingSchedule.getRoomNumber().equals(roomTaken) ||
                (existingSchedule.getTimeSchedule().equals(timeSchedule) && existingSchedule.getDaySchedule().equals(daySchedule));
    }

    public ClassScheduleResponseDTO addSchedule(ClassScheduleDTO classScheduleDTO) {
        Schedule schedule = convertToEntity(classScheduleDTO);
        scheduleRepository.save(schedule);
        return new ClassScheduleResponseDTO();
    }

    public ClassScheduleResponseDTO updateSchedule(int scheduleIdInt, ClassScheduleDTO updatedClassScheduleDTO) {
        // Retrieve the existing schedule by ID
        Schedule existingSchedule = scheduleRepository.findById(scheduleIdInt).orElse(null);

        // If schedule exists, update it; otherwise, do nothing
        if (existingSchedule != null) {
            updateEntityFromDTO(existingSchedule, updatedClassScheduleDTO);
            scheduleRepository.save(existingSchedule);
        }

        return new ClassScheduleResponseDTO();
    }

    private Schedule convertToEntity(ClassScheduleDTO classScheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setClassName(classScheduleDTO.getClassName());
        schedule.setDaySchedule(classScheduleDTO.getDaySchedule());
        schedule.setTimeSchedule(classScheduleDTO.getTimeSchedule());
        schedule.setBuildingName(classScheduleDTO.getBuildingName());
        schedule.setRoomNumber(classScheduleDTO.getRoomNumber());
        return schedule;
    }

    private void updateEntityFromDTO(Schedule schedule, ClassScheduleDTO updatedClassScheduleDTO) {
        schedule.setClassName(updatedClassScheduleDTO.getClassName());
        schedule.setDaySchedule(updatedClassScheduleDTO.getDaySchedule());
        schedule.setTimeSchedule(updatedClassScheduleDTO.getTimeSchedule());
        schedule.setBuildingName(updatedClassScheduleDTO.getBuildingName());
        schedule.setRoomNumber(updatedClassScheduleDTO.getRoomNumber());
    }
}
