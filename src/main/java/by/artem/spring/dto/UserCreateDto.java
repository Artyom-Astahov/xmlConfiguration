package by.artem.spring.dto;

import by.artem.spring.database.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public record UserCreateDto(User user) {

}
