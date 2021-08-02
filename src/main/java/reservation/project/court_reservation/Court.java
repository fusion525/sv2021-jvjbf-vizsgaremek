package reservation.project.court_reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long courtId;
    @Column(name = "court_open")
    private LocalDate openHour;
    @Column(name = "court_close")
    private LocalDate closeHour;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "court")
    private List<Reservation> reservations;

}
