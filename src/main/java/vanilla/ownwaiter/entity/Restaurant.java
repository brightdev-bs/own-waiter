package vanilla.ownwaiter.entity;

import lombok.Builder;
import lombok.Getter;
import vanilla.ownwaiter.entity.food.Food;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    private String description;

    @Lob
    private String qrCode;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();

    public Restaurant() {}
    @Builder
    public Restaurant(Long id, String name, String location, String profileImgUrl, String description, String qrCode) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.profileImgUrl = profileImgUrl;
        this.description = description;
        this.qrCode = qrCode;
    }

    public void setProfileImgUrl(String profileImg) {
        this.profileImgUrl = profileImg;
    }

    //Todo : 지우기
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
