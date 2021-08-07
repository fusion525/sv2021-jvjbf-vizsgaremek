package reservation.court;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reservation.reservation.Reservation;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Setter
public class CourtDTO {

    private Long courtId;
    @Schema(description = "Name of the court", example = "Squash court 1")
    private String courtName;
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(description = "Opening hour of court", example = "08:00:00")
    private LocalTime openHour;
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(description = "Closing hour of court", example = "23:00:00")
    private LocalTime closeHour;
    @Schema(description = "Type of court", example = "SQUASH")
    private CourtType courtType;
    private List<Reservation> reservations;

    private List<Long> reservationIds;

    public CourtDTO(Long courtId, String courtName, LocalTime openHour, LocalTime closeHour, CourtType courtType, List<Reservation> reservations) {
        this.courtId = courtId;
        this.courtName = courtName;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.courtType = courtType;
        this.reservations = reservations;

        this.reservationIds = this.reservations.stream().map(r -> r.getResId()).collect(Collectors.toList());
    }

    public Long getCourtId() {
        return courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public LocalTime getOpenHour() {
        return openHour;
    }

    public LocalTime getCloseHour() {
        return closeHour;
    }

    public CourtType getCourtType() {
        return courtType;
    }

    public List<Long> getReservationIds() {
        return reservationIds;
    }
}
