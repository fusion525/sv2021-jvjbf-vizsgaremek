package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetReservationCommand {

    private long courtId;
    private long resId;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;


    public GetReservationCommand(long courtId) {
        this.courtId = courtId;
    }

    public GetReservationCommand(LocalDateTime time) {
        this.time = time;
    }

    public GetReservationCommand(long courtId, LocalDateTime start, LocalDateTime end) {
        this.courtId = courtId;
        this.start = start;
        this.end = end;
    }
}
