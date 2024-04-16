package by.artem.spring.validator;

import by.artem.spring.dto.UserCreateEditDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto object, ConstraintValidatorContext constraintValidatorContext) {
        return hasText(object.getFirstname()) || hasText(object.getLastname());
    }
}
