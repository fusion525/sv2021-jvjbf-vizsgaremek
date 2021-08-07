package reservation.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.constraints.TimeConstraint;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeReservationCommand {

    @TimeConstraint
    private LocalDateTime startTime;
    @TimeConstraint
    private LocalDateTime endTime;
    private long courtId;
    private long customerId;

}
