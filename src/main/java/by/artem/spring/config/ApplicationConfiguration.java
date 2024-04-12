package by.artem.spring.config;


import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.database.repository.pool.ConnectionPool;

import by.artem.spring.dto.UserCreateDto;
import by.artem.spring.mapper.UserMapper;
import by.artem.spring.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;

@Configuration
//@Profile({"prod", "test"})
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool(@Value("${db.username}") String username,
                                         @Value("${db.password}") String password,
                                         @Value("${db.pool.size}") Integer poolSize,
                                         @Value("${db.url}") String url) {
        return new ConnectionPool(username, password, poolSize, url);
    }

    @Bean
    public UserRepository userRepository(ConnectionPool connectionPool, ApplicationEventPublisher applicationEventPublisher) {
        return new UserRepository(connectionPool, applicationEventPublisher);
    }

    @Bean
    public UserService userService(UserMapper userMapper, UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        return new UserService(userMapper, userRepository, applicationEventPublisher);
    }

    @Bean
    public UserCreateDto userCreateDto(){
        return new UserCreateDto(user());

    }

    @Bean User user(){
        return new User(1, "Artem");
    }


}
