package reservation.project.court_reservation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameConstraint,  String> {

    @Override
    public void initialize(NameConstraint nameConstraint) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        char chr = nameField.charAt(0);
        return !nameField.isEmpty() && nameField != null && Character.isUpperCase(chr);
    }

}
