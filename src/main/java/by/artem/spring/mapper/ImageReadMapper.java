package by.artem.spring.mapper;

import by.artem.spring.database.entity.Image;
import by.artem.spring.dto.ImageReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageReadMapper implements Mapper<Image, ImageReadDto>{
    @Override
    public ImageReadDto map(Image object) {
        return new ImageReadDto(
                object.getId(),
                object.getImage(),
                object.getUser()
        );
    }
}
