package reservation.project.court_reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("customer")
public class ReservationDTO {

    private long resId;
    private LocalDateTime time;
    private Customer customer;
    private Court court;

    private long courtId;
    private long custId;

    public long getResId() {
        return resId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public long getCourtId() {
        return court.getCourtId();
    }

    public long getCustId() {
        return customer.getCustId();
    }
}
