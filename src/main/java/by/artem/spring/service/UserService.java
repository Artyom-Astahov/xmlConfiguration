package by.artem.spring.service;

import by.artem.spring.database.entity.Image;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.ImageRepository;
import by.artem.spring.database.repository.UserRepository;

import by.artem.spring.dto.*;
import by.artem.spring.mapper.ImageCreateEditMapper;
import by.artem.spring.mapper.ImageReadMapper;
import by.artem.spring.mapper.UserCreateEditMapper;
import by.artem.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.artem.spring.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@ToString
@Slf4j
@Transactional(readOnly = true)
public class UserService implements UserDetailsService  {

    private final UserReadMapper userReadMapper;
    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ImageService imageService;
    private final ImageCreateEditMapper imageCreateEditMapper;
    private final ImageRepository imageRepository;
    private final ImageReadMapper imageReadMapper;

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
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return userCreateEditMapper.map(dto);
                })
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()){
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    uploadImage(userDto.getImage());
                    return userCreateEditMapper.map(userDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
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

    @Transactional
    public ImageReadDto addImageToGallery(ImageCreateEditDto imageCreateEditDto){

        return Optional.of(imageCreateEditDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return imageCreateEditMapper.map(dto);
                })
                .map(imageRepository::save)
                .map(imageReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean deleteImage(Long id){
        return imageRepository.findById(id)
                .map(entity -> {
                    imageRepository.delete(entity);
                    imageRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<ImageReadDto> findAllImageByUserId(Long id){
        return imageRepository.findAllByUserId(id)
                .stream().map(imageReadMapper::map)
                .toList();

    }

    @Transactional
    public Optional<ImageReadDto> updateImageByImageId(Long id, ImageCreateEditDto imageCreateEditDto){
        return imageRepository.findById(id)
                .map(entity -> {
                    uploadImage(imageCreateEditDto.getImage());
                    return imageCreateEditMapper.map(imageCreateEditDto, entity);
                })
                .map(imageRepository::saveAndFlush)
                .map(imageReadMapper::map);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }


}
