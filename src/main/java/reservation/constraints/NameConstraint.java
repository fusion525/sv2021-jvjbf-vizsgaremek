package reservation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameConstraint {

    String message() default "Invalid name (Name can not be blank and should start with Capital letter)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
