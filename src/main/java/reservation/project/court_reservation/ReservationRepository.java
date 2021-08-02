package reservation.project.court_reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT r from Reservation r where r.court.courtId = :id order by r.time")
    Collection<Reservation> findByCourtId(long id);

    @Query(value = "SELECT r from Reservation r where r.court.courtId = :courtId and r.time between :start and :end order by r.time")
    Collection<Reservation> findReservationByTimeByCourt(long courtId, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT r from Reservation r where r.time between :start and :end order by r.court.courtId")
    Collection<Reservation> findActualReservations(LocalDateTime start, LocalDateTime end);

}
