package vanilla.ownwaiter.entity.food;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.BaseEntity;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.user.Basket;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    private Basket basket;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;

        if(!restaurant.getFoods().contains(this)) {
            restaurant.getFoods().add(this);
        }
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Food() {
    }

    @Builder
    public Food(Long id, String name, int price, String description, FoodCategory category, String imgUrl, Restaurant restaurant, Basket basket) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imgUrl = imgUrl;
        this.restaurant = restaurant;
        this.basket = basket;
    }
}
