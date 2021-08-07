package reservation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TimeValidator implements ConstraintValidator<TimeConstraint, LocalDateTime> {

    @Override
    public void initialize(TimeConstraint timeConstraint) {
    }

    @Override
    public boolean isValid(LocalDateTime timeField, ConstraintValidatorContext cxt) {
        return timeField.isBefore(LocalDateTime.now());
    }

}
