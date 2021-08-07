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
    @TableGenerator(name = "Res_gen", table = "res_id_generator", pkColumnName = "res_name", pkColumnValue = "res_val", initialValue = 1, allocationSize = 100)
    @Column(name = "res_id")
    private Long resId;
    @Column(name = "res_time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public Reservation(LocalDateTime time, Court court, Customer customer) {
        this.time = time;
        this.court = court;
        this.customer = customer;
    }
}