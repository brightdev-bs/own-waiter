package vanilla.ownwaiter.entity.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vanilla.ownwaiter.entity.BaseEntity;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails  {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Nullable
    private UserSex sex;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @Nullable
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Transient
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public User(Long id, String username, String email, String password, @Nullable UserSex sex, UserRole role, @Nullable Restaurant restaurant, Basket basket) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.role = role;
        this.restaurant = restaurant;
        this.basket = basket;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void registerRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
        basket.setUser(this);
    }

}
