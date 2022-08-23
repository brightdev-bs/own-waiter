package vanilla.ownwaiter.entity.basket;

import vanilla.ownwaiter.entity.Order;

import javax.persistence.*;

@Entity
public class BasketOrder extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public void setOrder() {

    }

    public void setBasket() {

    }
}
