package by.artem.spring.validator;

import by.artem.spring.dto.UserCreateEditDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeMinValidator implements ConstraintValidator<AgeMin, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto userCreateEditDto, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDate.now().getYear() - userCreateEditDto.getBirthDate().getYear() > 18 ? true : false;
    }
}
