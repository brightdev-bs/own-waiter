package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;
import vanilla.ownwaiter.entity.BasketFood;
import vanilla.ownwaiter.entity.food.Food;
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

    public void setBasketFoods(BasketFood basketFood) {
        this.basketFoods = basketFoods;
    }
}
