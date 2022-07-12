package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Entity
public class Restaurant extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String location;
    private String profileImg;

    private String description;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    public Restaurant() {}

    @Builder

    public Restaurant(Long id, String name, String location, String profileImg, String description, List<Food> foods, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.profileImg = profileImg;
        this.description = description;
        this.foods = foods;
        this.orders = orders;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
