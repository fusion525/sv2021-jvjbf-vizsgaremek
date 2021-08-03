package reservation.project.court_reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long courtId;
    @Column(name = "court_open")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @Column(name = "court_close")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeHour;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "court")
    private List<Reservation> reservations;

    public Court(LocalTime openHour, LocalTime closeHour) {
        this.openHour = openHour;
        this.closeHour = closeHour;
    }
}
