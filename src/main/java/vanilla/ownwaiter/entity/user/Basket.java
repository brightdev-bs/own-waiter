package vanilla.ownwaiter.entity.user;

import lombok.Getter;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Basket {

    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private Long id;

    @OneToMany(mappedBy = "basket")
    private List<Food> foods;

    public void addItem(Food food) {
        this.foods.add(food);
        if(food.getBasket() != this) {
            food.setBasket(this);
        }
    }

    public Basket() {}

    public Basket(Long id, List<Food> foods) {
        this.id = id;
        this.foods = foods;
    }
}
