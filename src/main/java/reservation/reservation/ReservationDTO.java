package reservation.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.court.Court;
import reservation.customer.Customer;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties("customer")
public class ReservationDTO {

    @Schema(name = "Reservation ID", example = "1")
    private long resId;
    @Schema(name = "Reservation start time", example = "2021-08-15T22:00")
    private LocalDateTime startTime;
    @Schema(name = "Reservation end time", example = "2021-08-15T23:00")
    private LocalDateTime endTime;
    @Schema(name = "Court ID", example = "1")
    private long courtId;
    @Schema(name = "Customer ID", example = "1")
    private long custId;

    public ReservationDTO(Customer customer, Court court, LocalDateTime startTime, LocalDateTime endTime, long resId) {
        this.custId = customer.getCustId();
        this.courtId = court.getCourtId();
        this.resId = resId;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public ReservationDTO(LocalDateTime startTime, LocalDateTime endTime, Court court, Customer customer) {
        this.custId = customer.getCustId();
        this.courtId = court.getCourtId();
        this.startTime = startTime;
        this.endTime = endTime;

    }


}
