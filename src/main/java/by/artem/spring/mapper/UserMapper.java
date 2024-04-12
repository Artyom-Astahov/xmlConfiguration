package by.artem.spring.mapper;

import by.artem.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
@ToString
//@Profile({"prod", "test"})
public class UserMapper {

    private final UserCreateDto userCreateDto;
}
