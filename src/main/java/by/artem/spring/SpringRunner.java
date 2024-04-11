package by.artem.spring;


import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.config.ApplicationConfiguration;
import by.artem.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

            var companyService = context.getBean(CompanyService.class);
            companyService.findById(1);
            companyService.update(1);
            companyService.create(1);
            companyService.delete(1);

        }

    }
}
