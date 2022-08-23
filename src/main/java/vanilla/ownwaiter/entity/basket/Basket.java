package vanilla.ownwaiter.entity.basket;

import lombok.Getter;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.entity.restaurant.Restaurant;
import vanilla.ownwaiter.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Basket implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "basket")
    private User user;

    @OneToMany(mappedBy = "basket")
    private List<BasketFood> basketFoods = new ArrayList<>();

    @OneToMany(mappedBy = "basket", targetEntity = Order.class)
    private List<Order> orders = new ArrayList<>();

    public Basket() {}

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return this.basketFoods.get(0).getFood().getRestaurant();
    }
}
