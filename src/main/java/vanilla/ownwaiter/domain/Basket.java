package vanilla.ownwaiter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Basket {

    @Id
    private Long userId;
    private Long restaurantId;
    private Long itemId;

    public Basket() {}

    public Basket(Long userId, Long restaurantId, Long itemId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.itemId = itemId;
    }
}
