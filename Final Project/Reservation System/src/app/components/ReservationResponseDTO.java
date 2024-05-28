package app.components;

public class ReservationResponseDTO {

    private String message;

    public ReservationResponseDTO() {
        // Default constructor
    }

    public ReservationResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReservationResponseDTO: " + message;
    }
}
