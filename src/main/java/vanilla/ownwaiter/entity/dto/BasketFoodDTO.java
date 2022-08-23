package vanilla.ownwaiter.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BasketFoodDTO {

    private long restaurantId;
    private long foodId;
    private int price;
    private int quantity;
    private String foodName;
    private String imgUrl;
    private String description;

    @Builder
    public BasketFoodDTO(long restaurantId, long foodId, int price, int quantity, String foodName, String imgUrl, String description) {
        this.restaurantId = restaurantId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
        this.foodName = foodName;
        this.imgUrl = imgUrl;
        this.description = description;
    }
}
