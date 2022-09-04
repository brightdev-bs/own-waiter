package vanilla.ownwaiter.entity.basket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
public class BasketFood {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String submitFlag = "N";
    private String serveFlag = "N";

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public void setServeFlag(String serveFlag) {
        this.serveFlag = serveFlag;
    }

    public void setFood(Food food) {
        this.food = food;
        food.getBasketFoods().add(this);
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
<<<<<<< HEAD
        basket.getBasketFoods().add(this);
=======
        if(basket != null)
            basket.getBasketFoods().add(this);
>>>>>>> basketfood
    }

}
