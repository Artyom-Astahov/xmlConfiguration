package by.artem.spring.service;

import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ToString
public class UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
}
