package reservation.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.constraints.TimeConstraint;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyReservationCommand {

    private long reservationId;
    @TimeConstraint
    private LocalDateTime startTime;
    @TimeConstraint
    private LocalDateTime endTime;
    private long customerId;
    private long courtId;

}
