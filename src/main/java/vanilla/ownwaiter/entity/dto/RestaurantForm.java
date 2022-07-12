package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.entity.Img;
import vanilla.ownwaiter.entity.Restaurant;

@Data
public class RestaurantForm {

    private String name;
    private String location;
    private String description;
    private MultipartFile img;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .location(location)
                .description(description)
                .build();
    }
}
