package by.artem.spring;


import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.service.CompanyService;
import by.artem.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
        var userService = context.getBean(UserService.class);
        var companyService = context.getBean(CompanyService.class);
            System.out.println(userService);
            System.out.println(companyService);

        }

    }
}
