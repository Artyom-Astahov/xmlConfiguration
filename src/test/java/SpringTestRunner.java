import by.artem.spring.config.ApplicationConfiguration;
import by.artem.spring.database.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTestRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            System.out.println(context.getBean(UserRepository.class));

        }
    }
}
