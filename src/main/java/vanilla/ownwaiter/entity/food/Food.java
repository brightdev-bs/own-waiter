package vanilla.ownwaiter.entity.food;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.BaseEntity;
import vanilla.ownwaiter.entity.Img;
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
    @Embedded
    private Img img;

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
    public Food(Long id, String name, int price, String description, Img img, Restaurant restaurant, Basket basket) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.restaurant = restaurant;
        this.basket = basket;
    }
}
