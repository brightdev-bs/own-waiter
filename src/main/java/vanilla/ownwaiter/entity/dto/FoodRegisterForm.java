package vanilla.ownwaiter.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.entity.food.Food;

@Data
public class FoodRegisterForm {

    private String name;
    private String description;
    private int price;
    private MultipartFile img;

    public Food toEntity(FoodRegisterForm form, String uploadUrl) {
        return Food.builder()
                .name(form.name)
                .price(form.price)
                .description(form.description)
                .imgUrl(uploadUrl)
                .build();
    }
}
