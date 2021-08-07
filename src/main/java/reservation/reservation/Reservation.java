package reservation.reservation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.court.Court;
import reservation.customer.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(generator = "Res_gen")
    @TableGenerator(name = "Res_gen", table = "res_id_generator", pkColumnName = "res_name", pkColumnValue = "res_val", allocationSize = 100)
    @Column(name = "res_id")
    private Long resId;
    @Column(name = "res_start_time")
    private LocalDateTime startTime;
    @Column(name = "res_end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Court court, Customer customer) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.court = court;
        this.customer = customer;
    }
}
