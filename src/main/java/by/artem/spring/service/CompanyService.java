package by.artem.spring.service;

import by.artem.spring.database.repository.CompanyRepository;
import by.artem.spring.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
}
