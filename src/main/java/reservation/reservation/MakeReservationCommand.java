package reservation.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "Reservation start time", example = "2021-08-15T22:00")
    private LocalDateTime startTime;
    @TimeConstraint
    @Schema(name = "Reservation end time", example = "2021-08-15T23:00")
    private LocalDateTime endTime;
    @Schema(name = "Court ID", example = "1")
    private long courtId;
    @Schema(name = "Customer ID", example = "1")
    private long customerId;

}
