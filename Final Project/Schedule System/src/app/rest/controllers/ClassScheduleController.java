package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.ClassScheduleComponent;
import app.components.ClassScheduleDTO;
import app.components.ClassScheduleResponseDTO;
import app.entities.Schedule;

@Path("/class-schedules")
public class ClassScheduleController {

    @Autowired
    ClassScheduleComponent classSchedulesComponent;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClassScheduleResponseDTO addClassSchedule(ClassScheduleDTO classScheduleDTO) {
        try {
            // Validate input (you can add your own validation logic)
            if (classScheduleDTO == null) {
                return createErrorResponse("Invalid Input");
            }

            // Call the component to add the schedule
            return classSchedulesComponent.addSchedule(classScheduleDTO);
        } catch (Exception e) {
            // Handle server error
            return createErrorResponse("Server Error");
        }
    }

    @GET
    @Path("/update/{scheduleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClassScheduleResponseDTO updateClassSchedule(
            @QueryParam("scheduleID") String scheduleID,
            ClassScheduleDTO updatedClassScheduleDTO) {
        try {
            // Validate input (you can add your own validation logic)
            if (scheduleID == null || updatedClassScheduleDTO == null) {
                return createErrorResponse("Invalid Input");
            }

            // Convert scheduleID to int
            int scheduleIdInt = Integer.parseInt(scheduleID);

            // Call the component to update the schedule
            ClassScheduleResponseDTO response = classSchedulesComponent.updateSchedule(scheduleIdInt, updatedClassScheduleDTO);

            // Check if schedule was not found
            if (response == null) {
                return createErrorResponse("Schedule Not Found");
            }

            // Return the response
            return response;
        } catch (NumberFormatException e) {
            // Handle invalid scheduleID format
            return createErrorResponse("Invalid Schedule ID Format");
        } catch (Exception e) {
            // Handle other server errors
            return createErrorResponse("Server Error");
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schedule> viewAllClassSchedules() {
        return classSchedulesComponent.getAllSchedules();
    }

    // Helper method to create an error response with a message
    private ClassScheduleResponseDTO createErrorResponse(String errorMessage) {
        ClassScheduleResponseDTO errorResponse = new ClassScheduleResponseDTO();
        errorResponse.setMessage(errorMessage);
        return errorResponse;
    }
    
    @GET
    @Path("/rooms_taken")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> viewAllTakenRoomNumbers() {
        return classSchedulesComponent.getAllTakenRoomNumbers();
    }
    
    @POST
    @Path("/rooms_available")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getAllRoomNumbersNotInConflict(
            @FormParam("roomTaken") String roomTaken,
            @FormParam("daySchedule") String daySchedule,
            @FormParam("timeSchedule") String timeSchedule) {
        // Call the component to get the room numbers
        return classSchedulesComponent.getAllRoomNumbersNotInConflict(roomTaken, daySchedule, timeSchedule);
    }
}
