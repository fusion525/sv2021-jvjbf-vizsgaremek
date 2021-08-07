package reservation.court;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openHour;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeHour;
    @NotBlank
    @Length(min = 3, max = 25)
    private String courtName;

}
