package reservation.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.court.Court;
import reservation.customer.Customer;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties("customer")
public class ReservationDTO {

    private long resId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Customer customer;
    private Court court;
    private long courtId;
    private long custId;

    public ReservationDTO(long resId, LocalDateTime startTime, LocalDateTime endTime, Customer customer, Court court) {
        this.resId = resId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customer = customer;
        this.court = court;
        this.courtId = this.court.getCourtId();
        this.custId = this.customer.getCustId();
    }

    public long getResId() {
        return resId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getCourtId() {
        return courtId;
    }

    public long getCustId() {
        return custId;
    }
}
