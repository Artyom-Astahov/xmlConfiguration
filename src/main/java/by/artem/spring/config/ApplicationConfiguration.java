package by.artem.spring.config;


import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.database.repository.pool.ConnectionPool;
import by.artem.web.WebConfiguration;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@ImportResource("classpath:application.xml")
@Configuration
@ComponentScan("by.artem.spring")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    @Profile("prod")
    public ConnectionPool connectionPool(){
        return new ConnectionPool("postgres", "root", 20, "url");
    }

    @Bean
    public UserRepository userRepository(ConnectionPool connectionPool){
        return new UserRepository(connectionPool);
    }
}
