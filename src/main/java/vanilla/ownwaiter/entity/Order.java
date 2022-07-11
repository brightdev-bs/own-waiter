package vanilla.ownwaiter.entity;


import lombok.Getter;
import vanilla.ownwaiter.entity.user.User;

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
    @JoinColumn(name = "user_id")
    private User user;
}
