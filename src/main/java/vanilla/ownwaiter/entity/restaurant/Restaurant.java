package vanilla.ownwaiter.entity.restaurant;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.basket.BaseEntity;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Restaurant extends BaseEntity implements Serializable {

    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String location;
    private String profileImgUrl;

    private String qrCodeUrl;

    private String description;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();

    public Restaurant() {}
    @Builder
    public Restaurant(Long id, String name, String location, String profileImgUrl, String description, String qrCodeUrl) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.profileImgUrl = profileImgUrl;
        this.description = description;
        this.qrCodeUrl = qrCodeUrl;
    }

    public void setProfileImgUrl(String profileImg) {
        this.profileImgUrl = profileImg;
    }

    public void setQrCodeUrl(String url) {
        this.qrCodeUrl = url;
    }
}
