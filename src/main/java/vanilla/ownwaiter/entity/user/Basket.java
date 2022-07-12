package vanilla.ownwaiter.entity.user;

import lombok.Getter;
import vanilla.ownwaiter.entity.BasketFood;
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
    private List<BasketFood> basketFoods;

    public Basket() {}

}
