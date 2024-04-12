package spring.config;

import by.artem.spring.database.entity.Company;
import by.artem.spring.database.repository.CompanyRepository;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.database.repository.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;

@Configuration
@Profile("test")
public class ApplicationConfig {

    @Bean
    public ConnectionPool connectionPool(@Value("${db.username}") String username,
                                         @Value("${db.password}") String password,
                                         @Value("${db.pool.size}") Integer poolSize,
                                         @Value("${db.url}") String url){
        return new ConnectionPool(username, password, poolSize, url);
    }


}
