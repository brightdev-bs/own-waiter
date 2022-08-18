package vanilla.ownwaiter.entity.food.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.food.FoodCategory;

@Data
public class FoodRegisterForm {

    private String name;
    private String description;
    private int price;
    private String category;
    private MultipartFile img;

    public Food toEntity(FoodRegisterForm form, String uploadUrl) {
        return Food.builder()
                .name(form.name)
                .price(form.price)
                .description(form.description)
                .category(FoodCategory.valueOf(category))
                .imgUrl(uploadUrl)
                .build();
    }
}
