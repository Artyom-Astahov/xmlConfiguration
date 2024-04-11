package by.artem.spring;


import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.config.ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            System.out.println(context.getBean(UserRepository.class));

        }

    }
}
