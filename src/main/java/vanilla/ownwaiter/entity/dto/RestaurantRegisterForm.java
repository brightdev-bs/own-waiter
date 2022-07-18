package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.file.S3Uploader;

import javax.persistence.Entity;
import java.io.IOException;

@Data
public class RestaurantRegisterForm {

    private String name;
    private String location;
    private String description;
    private MultipartFile img;

    public RestaurantRegisterForm() {
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .location(location)
                .description(description)
                .build();
    }
}
