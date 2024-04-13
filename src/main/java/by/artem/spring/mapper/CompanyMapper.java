package by.artem.spring.mapper;

import by.artem.spring.dto.CompanyReadDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class CompanyMapper {
    private final CompanyReadDto companyDto;
}
