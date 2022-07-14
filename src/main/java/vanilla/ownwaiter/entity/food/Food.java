package vanilla.ownwaiter.entity.food;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;
import vanilla.ownwaiter.entity.BaseEntity;
import vanilla.ownwaiter.entity.BasketFood;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Food extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;
    private String name;
    private int price;
    private String description;
    @Enumerated(EnumType.STRING)
    private FoodCategory category;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "food")
    private List<BasketFood> basketFoods = new ArrayList<>();

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;

        if(!restaurant.getFoods().contains(this)) {
            restaurant.getFoods().add(this);
        }
    }

    public Food() {
    }

    @Builder
    public Food(Long id, String name, int price, String description, FoodCategory category, String imgUrl, Restaurant restaurant, List<BasketFood> basketFoods) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imgUrl = imgUrl;
        this.restaurant = restaurant;
        this.basketFoods = basketFoods;
    }
}
