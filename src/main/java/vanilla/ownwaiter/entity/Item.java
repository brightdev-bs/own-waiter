package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.user.Basket;

import javax.persistence.*;

@Getter
@Entity
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;

    private String description;
    @Embedded
    private Img img;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    private Basket basket;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;

        if(!restaurant.getItems().contains(this)) {
            restaurant.getItems().add(this);
        }
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Item() {
    }

    @Builder
    public Item(Long id, String name, int price, String description, Img img, Restaurant restaurant, Basket basket) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.restaurant = restaurant;
        this.basket = basket;
    }
}
