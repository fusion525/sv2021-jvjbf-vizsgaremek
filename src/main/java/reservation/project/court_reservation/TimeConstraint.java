package reservation.project.court_reservation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConstraint {

    String message() default "Can not make reservation in the past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
