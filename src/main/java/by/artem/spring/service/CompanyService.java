package by.artem.spring.service;

import by.artem.spring.database.entity.Company;
import by.artem.spring.database.repository.CompanyRepository;
import by.artem.spring.dto.CompanyReadDto;
import by.artem.spring.listener.AccessType;
import by.artem.spring.listener.AfterEntityEvent;
import by.artem.spring.listener.BeforeEntityEvent;
import by.artem.spring.mapper.CompanyReadMapper;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyReadMapper companyReadMapper;
    private final ApplicationEventPublisher applicationEventPublisher;



    public Optional<CompanyReadDto> findById(Integer id) {
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.READ));
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId(), null);
        });
    }


    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }
}
