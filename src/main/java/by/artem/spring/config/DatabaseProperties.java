package by.artem.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "db")
//@Profile({"prod", "test"})
public record DatabaseProperties(String username,
                                 String password,
                                 String driver,
                                 String url,
                                 String hosts,
                                 PoolProperties pool) {

    public static record PoolProperties(Integer size,
                                        Integer timeout){

    }
}
