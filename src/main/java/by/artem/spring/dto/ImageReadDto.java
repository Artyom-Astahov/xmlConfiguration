package by.artem.spring.dto;

import by.artem.spring.database.entity.User;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
public class ImageReadDto {
    Long id;
    String image;
    User user;
}
