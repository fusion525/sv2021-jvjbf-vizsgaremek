package reservation.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteReservationCommand {

    @Schema(description = "Reservation ID", example = "1")
    private long id;

}
