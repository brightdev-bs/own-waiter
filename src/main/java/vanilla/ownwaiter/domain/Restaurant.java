package vanilla.ownwaiter.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Restaurant {

    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    private String name;
    private String location;
    @Embedded
    private Img logo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    public Restaurant() {}

    public Restaurant(String name, String location, Img logo) {
        this.name = name;
        this.location = location;
        this.logo = logo;
    }

}
