package by.artem.spring.mapper;

import by.artem.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
@ToString
public class UserMapper {

    @Autowired
    private final UserDto userDto;
}
