package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Restaurant extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    private String name;
    private String location;
    @Embedded
    private Img logo;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    public Restaurant() {}

    @Builder
    public Restaurant(Long id, String name, String location, Img logo, List<Item> items) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.logo = logo;
        this.items = items;
    }
}
