package by.artem.spring.database.repository;

import by.artem.spring.database.entity.Company;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.bpp.InjectBean;
import by.artem.spring.database.repository.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ToString
//@Profile({"prod", "test"})
public class UserRepository {

    @Autowired
    private ConnectionPool connectionPool;
    private final ApplicationEventPublisher applicationEventPublisher;


    public UserRepository(ConnectionPool connectionPool, ApplicationEventPublisher applicationEventPublisher) {
        this.connectionPool = connectionPool;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Optional<User> create(Integer id, String name){
        System.out.println("UserRepository create method");
        return Optional.of(new User(id, name));
    }
}
