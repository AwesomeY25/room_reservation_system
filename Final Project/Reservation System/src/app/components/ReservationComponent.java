package app.components;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Reservation;
import app.repositories.ReservationRepository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class ReservationComponent {

    // Retrofit
    private final Retrofit retrofit;

    @Autowired
    private ReservationRepository rRepo;

    public ReservationComponent() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://bogus")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ReservationResponseDTO makeReservation(ReservationDTO reservationDTO) throws IOException {
        if (reservationDTO == null) {
            return new ReservationResponseDTO("Invalid Input");
        }

        Reservation reservation = convertToEntity(reservationDTO);
        reservation.setStatus("Pending"); // Set the status to "Pending"
        rRepo.save(reservation);

        return new ReservationResponseDTO("Reservation made successfully");
    }

    public ReservationResponseDTO cancelReservation(Long reservationId) {
        try {
            if (reservationId == null) {
                return new ReservationResponseDTO("Invalid Input");
            }

            Optional<Reservation> existingReservation = rRepo.findById(reservationId);
            if (!existingReservation.isPresent()) {
                return new ReservationResponseDTO("Reservation Not Found");
            }

            rRepo.deleteById(reservationId);

            return new ReservationResponseDTO("Reservation canceled successfully");
        } catch (Exception e) {
            return new ReservationResponseDTO("Server Error");
        }
    }

    public ReservationResponseDTO rejectReservation(Long reservationId) {
        try {
            if (reservationId == null) {
                return new ReservationResponseDTO("Invalid Input");
            }

            Optional<Reservation> existingReservation = rRepo.findById(reservationId);
            if (!existingReservation.isPresent()) {
                return new ReservationResponseDTO("Reservation Not Found");
            }

            Reservation reservation = existingReservation.get();
            reservation.setStatus("Rejected"); // Set the status to "Rejected"
            rRepo.save(reservation);

            ReservationIF notifierAdminService = retrofit.create(ReservationIF.class);

            String rejectMessage = "Reservation with ID " + reservationId + " has been rejected.";

            String email = reservation.getStudentContact();

            // Send reject notification to Notifier Admin
            Call<ResponseBody> call = notifierAdminService.send(
                    email,
                    "Reservation Rejected",
                    rejectMessage
            );

            Response<ResponseBody> response = call.execute();

            if (response.isSuccessful()) {
                return new ReservationResponseDTO("Email for reservation ID: " + reservationId + " was sent successfully!");
            } else {
                return new ReservationResponseDTO("Failed to send email");
            }

        } catch (Exception e) {
            return new ReservationResponseDTO("Server Error");
        }
    }

    public ReservationResponseDTO acceptReservation(Long reservationId) {
        try {
            if (reservationId == null) {
                return new ReservationResponseDTO("Invalid Input");
            }

            Optional<Reservation> existingReservation = rRepo.findById(reservationId);
            if (!existingReservation.isPresent()) {
                return new ReservationResponseDTO("Reservation Not Found");
            }

            Reservation reservation = existingReservation.get();
            reservation.setStatus("Accepted"); // Set the status to "Accepted"
            rRepo.save(reservation);

            ReservationIF notifierAdminService = retrofit.create(ReservationIF.class);

            String acceptMessage = "Reservation with ID " + reservationId + " has been accepted.";

            String email = reservation.getStudentContact();

            // Send accept notification to Notifier Admin
            Call<ResponseBody> call = notifierAdminService.send(email, "Reservation Accepted", acceptMessage);

            Response<ResponseBody> response = call.execute();

            if (response.isSuccessful()) {
                return new ReservationResponseDTO("Email for reservation ID: " + reservationId + " was sent successfully!");
            } else {
                return new ReservationResponseDTO("Failed to send email");
            }

        } catch (Exception e) {
            return new ReservationResponseDTO("Server Error");
        }
    }

    public ReservationResponseDTO suggestRoomNumber(Long reservationId) throws IOException {
        if (reservationId == null) {
            return new ReservationResponseDTO("Invalid Input");
        }

        Optional<Reservation> existingReservation = rRepo.findById(reservationId);
        if (!existingReservation.isPresent()) {
            return new ReservationResponseDTO("Reservation Not Found");
        }

        Reservation reservation = existingReservation.get();

        // Fetch all existing reservations from the repository
        List<Reservation> allReservations = rRepo.findAll();

        // Check for conflicts with existing reservations
        boolean hasConflicts = allReservations.stream()
                .anyMatch(existing -> isConflict(reservation, existing));

        if (hasConflicts) {
            return new ReservationResponseDTO("There is a conflict with existing reservations");
        } else {
            // Continue with your existing logic to suggest room number
            ReservationIF scheduleService = retrofit.create(ReservationIF.class);

            String roomTaken = reservation.getClassroomCode();
            String daySchedule = reservation.getDaySchedule();
            String timeSchedule = reservation.getTimeslot();

            Call<ResponseBody> call = scheduleService.check(
                    roomTaken,
                    daySchedule,
                    timeSchedule);

            Response<ResponseBody> response = call.execute();

            if (response.isSuccessful()) {
                List<String> possibleRooms = Arrays.asList(response.body().string().split(","));

                possibleRooms = possibleRooms.stream()
                        .map(String::trim)
                        .collect(Collectors.toList());

                return new ReservationResponseDTO("Suggested: " + possibleRooms);
            } else {
                return new ReservationResponseDTO("Failed to suggest room number");
            }
        }
    }

    private boolean isConflict(Reservation newReservation, Reservation existingReservation) {
        // Check if the reservations have the same reservationId
        return !newReservation.getReservationID().equals(existingReservation.getReservationID())
                && isScheduleConflict(newReservation, existingReservation);
    }

    private boolean isScheduleConflict(Reservation newReservation, Reservation existingReservation) {
        // Implement your logic to check if the schedules conflict
        // You may compare day, time, or any other relevant attributes
        // For example, if they are in the same room and at the same time, it conflicts
        return newReservation.getClassroomCode().equals(existingReservation.getClassroomCode())
                && newReservation.getDaySchedule().equals(existingReservation.getDaySchedule())
                && newReservation.getTimeslot().equals(existingReservation.getTimeslot());
    }

    public List<ReservationDTO> allReservation() {
        try {
            List<Reservation> reservations = rRepo.findAll();

            return reservations.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return null;
        }
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationID(reservationDTO.getReservationID());
        reservation.setClassroomCode(reservationDTO.getClassroomCode());
        reservation.setStudentContact(reservationDTO.getStudentContact());
        reservation.setTimeslot(reservationDTO.getTimeslot());
        reservation.setStudentName(reservationDTO.getStudentName());
        reservation.setStudentContact(reservationDTO.getStudentContact());
        reservation.setCourseCode(reservationDTO.getCourseCode());
        reservation.setDaySchedule(reservationDTO.getDaySchedule());

        return reservation;
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationID(reservation.getReservationID());
        reservationDTO.setClassroomCode(reservation.getClassroomCode());
        reservationDTO.setReservationID(reservation.getReservationID());
        reservationDTO.setClassroomCode(reservation.getClassroomCode());
        reservationDTO.setStudentContact(reservation.getStudentContact());
        reservationDTO.setTimeslot(reservation.getTimeslot());
        reservationDTO.setStudentContact(reservation.getStudentContact());
        reservationDTO.setCourseCode(reservation.getCourseCode());
        reservationDTO.setDaySchedule(reservation.getDaySchedule());

        return reservationDTO;
    }
}