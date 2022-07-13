package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    private String profileImgUrl;

    private String description;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();

    public Restaurant() {}

    @Builder
    public Restaurant(Long id, String name, String location, String profileImgUrl, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.profileImgUrl = profileImgUrl;
        this.description = description;
    }

    public void setProfileImgUrl(String profileImg) {
        this.profileImgUrl = profileImg;
    }
}
