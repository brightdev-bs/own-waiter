package vanilla.ownwaiter.entity;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vanilla.ownwaiter.entity.basket.BaseEntity;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.entity.restaurant.Restaurant;
import vanilla.ownwaiter.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Slf4j
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
    private String completeFlag = "N";

    public Order() {
    }

    public Order(Restaurant restaurant, Basket basket, String userRequest) {
        this.restaurant = restaurant;
        this.basket = basket;
        this.userRequest = userRequest;
    }

    public String getFoodNames() {
        List<BasketFood> basketFoods = getBasket().getBasketFoods();
        StringBuilder sb = new StringBuilder();
        for (BasketFood basketFood : basketFoods) {
            sb.append(basketFood.getFood().getName() + " , " + basketFood.getQuantity() + "개 | ");
        }
        return sb.toString();
    }

    public void setCompleteFlag() {
        log.info("setCompleteFlag 실행됨.");
        this.completeFlag = "Y";
    }
}
