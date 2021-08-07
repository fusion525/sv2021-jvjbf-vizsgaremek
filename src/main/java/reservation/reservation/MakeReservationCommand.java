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
    private LocalDateTime time;
    private long courtId;
    private long customerId;

}
