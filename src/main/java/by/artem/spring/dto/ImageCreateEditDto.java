package by.artem.spring.dto;


import by.artem.spring.database.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCreateEditDto {
    MultipartFile image;
    User user;
}
