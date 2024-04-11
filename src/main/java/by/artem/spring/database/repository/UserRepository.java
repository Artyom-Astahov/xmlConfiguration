package by.artem.spring.database.repository;

import by.artem.spring.database.repository.bpp.InjectBean;
import by.artem.spring.database.repository.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@ToString
@Profile({"prod", "test"})
public class UserRepository {
    @Autowired
    private ConnectionPool connectionPool;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserRepository(ConnectionPool connectionPool, ApplicationEventPublisher applicationEventPublisher) {
        this.connectionPool = connectionPool;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void create(){

    }
}
