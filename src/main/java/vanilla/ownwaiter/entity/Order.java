package vanilla.ownwaiter.entity;


import lombok.Getter;
import vanilla.ownwaiter.entity.basket.BaseEntity;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.restaurant.Restaurant;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String userRequest;

    public Order() {
    }

    public Order(Restaurant restaurant, Basket basket, String userRequest) {
        this.restaurant = restaurant;
        this.basket = basket;
        this.userRequest = userRequest;
    }
}
