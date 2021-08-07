package reservation.reservation;

public class InvalidReservationException extends RuntimeException{

    public InvalidReservationException(String message) {
        super(message);
    }
}
