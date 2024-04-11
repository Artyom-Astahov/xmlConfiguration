package spring.config;

import by.artem.spring.database.repository.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("spring")
@PropertySource("classpath:application.properties")
@Profile("test")
public class ApplicationConfig {

    @Bean
    public ConnectionPool connectionPool(){
        return new ConnectionPool("postgres", "root", 20, "url");
    }
}
