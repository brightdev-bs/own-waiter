package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private String price;

    private String description;
    @Embedded
    private Img img;

    @ManyToOne
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
    public Item(Long id, String name, String price, String description, Img img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }
}
