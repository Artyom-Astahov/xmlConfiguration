package by.artem.integration.repository;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.Company;
import by.artem.spring.database.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;


import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryTest {


    private final CompanyRepository companyRepository;
    private final EntityManager entityManager;


    @Test
    void checkFindByQueries(){
        companyRepository.findByName("Google");
        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
        assertThat(companies).hasSize(2);
    }

    @Test
    void delete(){
        Company apple = Company.builder()
                .name("Apple")
                .build();
        entityManager.persist(apple);
        Integer id = apple.getId();
        var maybeCompany = companyRepository.findById(id);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(id).isEmpty());
    }
    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save(){
        var company = Company.builder()
                .name("Apple1")
                .locales(
                        Map.of(
                                "ru", "Apple описание",
                                "en", "Apple description"
                        )
                ).build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }

    @Test
    void updateCompanyById(){
        var result = companyRepository.updateCompanyNameById(1, "Yandex");
        Company company = entityManager.find(Company.class, 1);
        assertThat(company.getName()).contains("Yandex");
    }

    @Test
    void deleteCompanyByNameContaining(){
        companyRepository.deleteCompanyByNameContaining('A');
        var company = entityManager.find(Company.class, 3);
        assertNull(company);
    }
}
