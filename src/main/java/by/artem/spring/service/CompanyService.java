package by.artem.spring.service;

import by.artem.spring.database.entity.Company;
import by.artem.spring.database.repository.CompanyRepository;
import by.artem.spring.dto.CompanyReadDto;
import by.artem.spring.listener.AccessType;
import by.artem.spring.listener.AfterEntityEvent;
import by.artem.spring.listener.BeforeEntityEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CompanyService {
//    private final CompanyRepository companyRepository;
//    private final ApplicationEventPublisher applicationEventPublisher;
//
//
//
//    public CompanyService(CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher) {
//        this.companyRepository = companyRepository;
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//
//    public Optional<CompanyReadDto> findById(Integer id) {
//        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.READ));
//        return companyRepository.findById(id).map(entity -> {
//            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.READ));
//            return new CompanyReadDto(entity.getId());
//        });
//    }



}
