package vanilla.ownwaiter.entity;


import lombok.Getter;
import vanilla.ownwaiter.entity.basket.BaseEntity;

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

    //
}
