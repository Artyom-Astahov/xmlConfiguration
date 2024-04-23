package by.artem.spring.mapper;

import by.artem.spring.database.entity.Image;
import by.artem.spring.dto.ImageCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ImageCreateEditMapper implements Mapper<ImageCreateEditDto, Image> {
    @Override
    public Image map(ImageCreateEditDto object) {

        Image imageObject = new Image();
        imageObject.setUser(object.getUser());

        Optional.ofNullable(object.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> imageObject.setImage(image.getOriginalFilename()));
        return imageObject;
    }

    @Override
    public Image map(ImageCreateEditDto fromObject, Image toObject) {
        Optional.ofNullable(fromObject.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> toObject.setImage(image.getOriginalFilename()));
        return toObject;
    }
}
