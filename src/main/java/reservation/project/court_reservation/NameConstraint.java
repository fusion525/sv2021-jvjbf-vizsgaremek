package reservation.project.court_reservation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameConstraint {

    String message() default "Invalid name (Name should start with Capital letter)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
