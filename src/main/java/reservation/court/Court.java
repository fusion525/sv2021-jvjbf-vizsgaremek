package reservation.court;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservation.reservation.Reservation;

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
    @Column(name = "court_name", length = 200, nullable = false)
    private String courtName;
    @Column(name = "court_open")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @Column(name = "court_close")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeHour;
    @Column(name = "court_type", length = 200)
    @Enumerated(value = EnumType.STRING)
    private CourtType courtType;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "court")
    private List<Reservation> reservations;

    public Court(String courtName, LocalTime openHour, LocalTime closeHour, CourtType courtType) {
        this.courtName = courtName;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.courtType = courtType;
    }
}
