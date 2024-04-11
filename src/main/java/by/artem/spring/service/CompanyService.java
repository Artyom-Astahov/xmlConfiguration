package by.artem.spring.service;

import by.artem.spring.database.repository.CompanyRepository;
import by.artem.spring.dto.CompanyCreateDto;
import by.artem.spring.dto.CompanyDeleteDto;
import by.artem.spring.dto.CompanyReadDto;
import by.artem.spring.dto.CompanyUpdateDto;
import by.artem.spring.listener.AccessType;
import by.artem.spring.listener.AfterEntityEvent;
import by.artem.spring.listener.BeforeEntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyService(CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.companyRepository = companyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.READ));
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.id());
        });
    }

    public Optional<CompanyUpdateDto> update(Integer id) {
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.UPDATE));
        return companyRepository.update(id).map(entity -> {
            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.UPDATE));
            return new CompanyUpdateDto(entity.id());
        });
    }

    public Optional<CompanyCreateDto> create(Integer id) {
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.CREATE));
        return companyRepository.create(id).map(entity -> {
            applicationEventPublisher.publishEvent(new AfterEntityEvent(entity, AccessType.CREATE));
            return new CompanyCreateDto(entity.id());
        });
    }

    public void delete(Integer id) {
        applicationEventPublisher.publishEvent(new BeforeEntityEvent(this, AccessType.DELETE));
        companyRepository.delete(id);
        applicationEventPublisher.publishEvent(new AfterEntityEvent(this, AccessType.DELETE));

    }
}
