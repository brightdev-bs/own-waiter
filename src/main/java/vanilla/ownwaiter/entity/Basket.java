package vanilla.ownwaiter.entity;

import lombok.Getter;

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
    private List<Item> items;

    public void addItem(Item item) {
        this.items.add(item);
        if(item.getBasket() != this) {
            item.setBasket(this);
        }
    }

    public Basket() {}

    public Basket(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }
}
