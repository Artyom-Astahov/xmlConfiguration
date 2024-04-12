package by.artem.integration.service;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.User;
import by.artem.spring.dto.UserCreateDto;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {
    private final UserService userService;
    private static final Integer User_ID = 1;
    private static final String NAME = "Artem";

    @Test
    void create() {
        User user = new User(User_ID, NAME);
        UserCreateDto userDto = new UserCreateDto(user);
        var actualResult = userService.create(userDto);
        assertTrue(actualResult.isPresent());

        var expectedResult = new UserCreateDto(new User(User_ID, NAME));
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
