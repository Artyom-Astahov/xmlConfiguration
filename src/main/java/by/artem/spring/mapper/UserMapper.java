package by.artem.spring.mapper;

import by.artem.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class UserMapper {

    private final UserDto userDto;
}
