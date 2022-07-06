package vanilla.ownwaiter.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private String price;

    private String description;
    @Embedded
    private Img img;

    public Item(String name, String price, Img img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }
    public Item() {

    }
}
