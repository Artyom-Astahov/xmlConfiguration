package by.artem.spring.service;

import by.artem.spring.database.repository.UserRepository;

import by.artem.spring.dto.UserCreateDto;
import by.artem.spring.listener.AccessType;
import by.artem.spring.listener.AfterEntityEvent;
import by.artem.spring.listener.BeforeEntityEvent;
import by.artem.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@ToString
@Slf4j
//@Profile({"prod", "test"})
public class UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<UserCreateDto> create(UserCreateDto userCreateDto){
        log.info("Создание нового User");
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.CREATE));
        return userRepository.create(userCreateDto.user().id(), userCreateDto.user().name()).map(entity -> {
            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.CREATE));
            return new UserCreateDto(entity);
        });
    }
}
