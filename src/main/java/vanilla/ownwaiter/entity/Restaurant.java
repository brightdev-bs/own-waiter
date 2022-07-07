package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
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
    @JoinColumn(name = "restaurant_id")
    private List<Item> items;

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
