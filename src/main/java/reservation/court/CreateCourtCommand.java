package reservation.court;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourtCommand {

    @Schema(description = "Open hour of court", example = "08:00:00")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(description = "Closing hour of court", example = "23:00:00")
    private LocalTime closeHour;
    @NotBlank
    @Length(min = 3, max = 25)
    @Schema(description = "Name of the court", example = "Squash court 1")
    private String courtName;
    @Schema(description = "Type of Court", example = "SQUASH")
    private CourtType courtType;

}
