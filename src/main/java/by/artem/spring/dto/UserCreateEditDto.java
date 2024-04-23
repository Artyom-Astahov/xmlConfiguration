package by.artem.spring.dto;

import by.artem.spring.database.entity.Role;
import by.artem.spring.validator.AgeMin;
import by.artem.spring.validator.UserInfo;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
@AgeMin
public class UserCreateEditDto {
    @Email
    String username;
    String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birthDate;

    @Size(min = 3, max = 30)
    String firstname;

    String lastname;
    Role role;
    Integer companyId;
    MultipartFile image;
}
