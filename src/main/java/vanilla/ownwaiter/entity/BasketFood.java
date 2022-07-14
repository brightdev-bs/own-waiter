package vanilla.ownwaiter.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Entity
@Getter
public class BasketFood {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public void setFood(Food food) {
        this.food = food;
        food.getBasketFoods().add(this);
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
        basket.getBasketFoods().add(this);
    }

}
