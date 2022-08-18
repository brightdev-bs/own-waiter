package vanilla.ownwaiter.entity.restaurant.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.entity.restaurant.Restaurant;

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
