package by.artem.integration.repository;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.Role;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void findAllByFirstnameContainingAndLastnameContaining() {
        List<User> users = userRepository.findAllByFirstnameContainingAndLastnameContaining("a", "a");
        assertThat(users).hasSize(3);
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        Page<User> page = userRepository.findAllBy(pageable);
        page.forEach(u -> System.out.println(u.getId()));
        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(u -> System.out.println(u.getId()));
            System.out.println(page.getTotalPages());
        }
    }

    @Test
    void findFirstByCompanyIsNotNullOrderByIdDeskTest() {
        var user = userRepository.findFirstBy(Sort.by("id").descending());
        assertTrue(user.isPresent());
        user.ifPresent(u -> assertEquals("Kate", u.getFirstname()));
    }

    @Test
    void findByRoleAndBirthDateBetween() {
        var users = userRepository.findByRoleAndBirthDateBetween(Role.ADMIN, LocalDate.of(1980, Month.JANUARY, 01), LocalDate.of(1990, Month.JANUARY, 01));
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getFirstname()).contains("Kate");
    }

    @Test
    void findFourFirst() {
        var users = userRepository.findFirst4By();
        assertThat(users).hasSize(4);
    }

    @Test
    void findAllBy() {
        var users = userRepository.findAllBy(Sort.by("birthDate"));
        assertThat(users.get(0).getFirstname()).contains("Vlad");
        users = userRepository.findAllBy(Sort.by("birthDate")
                .and(Sort.by("firstname")
                        .and(Sort.by("lastname")
                        )
                )
        );
        assertThat(users.get(0).getFirstname()).contains("Kate");
    }

    @Test
    void findAllByRole(){
        var pageable = PageRequest.of(0, 3, Sort.by("lastname").descending());
        var users = userRepository.findAllByRole(Role.USER, pageable);
        assertNotNull(users);
        users.stream().map(user -> assertThat(user.getRole()).isEqualTo(Role.USER));
        assertThat(users.get(0).getLastname()).contains("Vladikov");

    }


}
