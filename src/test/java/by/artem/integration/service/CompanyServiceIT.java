package by.artem.integration.service;

import by.artem.spring.config.DatabaseProperties;
import by.artem.spring.dto.CompanyReadDto;
import by.artem.spring.service.CompanyService;
import by.artem.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

    }

}
