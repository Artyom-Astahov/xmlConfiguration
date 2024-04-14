package by.artem.spring.config;



import by.artem.spring.database.repository.pool.ConnectionPool;


import org.springframework.beans.factory.annotation.Value;

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









}
