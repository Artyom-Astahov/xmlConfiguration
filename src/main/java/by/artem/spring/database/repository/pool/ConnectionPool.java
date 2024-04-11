package by.artem.spring.database.repository.pool;

import by.artem.spring.database.repository.bpp.InjectBean;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Profile({"prod","test"})
@ToString
public class ConnectionPool {

    private String username;
    private String password;
    private Integer poolSize;
    private String url;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.password}") String password,
                          @Value("${db.pool.size}") Integer poolSize,
                          @Value("${db.url}") String url) {
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.url = url;
    }
}

