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
    private LocalDateTime time;
    private Customer customer;
    private Court court;

    private long courtId;
    private long custId;

    public ReservationDTO(long resId, LocalDateTime time, Customer customer, Court court) {
        this.resId = resId;
        this.time = time;
        this.customer = customer;
        this.court = court;
        this.courtId = court.getCourtId();
        this.custId = customer.getCustId();
    }

    public long getResId() {
        return resId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public long getCourtId() {
        return courtId;
    }

    public long getCustId() {
        return custId;
    }
}
