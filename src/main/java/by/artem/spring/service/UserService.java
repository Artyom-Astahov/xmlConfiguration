package by.artem.spring.service;

import by.artem.spring.database.repository.UserRepository;

import by.artem.spring.dto.QPredicates;
import by.artem.spring.dto.UserCreateEditDto;
import by.artem.spring.dto.UserFilter;
import by.artem.spring.dto.UserReadDto;
import by.artem.spring.mapper.UserCreateEditMapper;
import by.artem.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.artem.spring.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@ToString
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserReadMapper userReadMapper;
    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }
    public List<UserReadDto> findAll(UserFilter filter) {
        return userRepository.findAllByFilter(filter).stream()
                .map(userReadMapper::map)
                .toList();
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }
    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }


}
