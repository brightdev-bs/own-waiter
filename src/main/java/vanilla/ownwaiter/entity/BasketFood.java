package vanilla.ownwaiter.entity;

import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.Basket;

import javax.persistence.*;

@Entity
public class BasketFood {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;


}
