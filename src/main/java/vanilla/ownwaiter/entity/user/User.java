package vanilla.ownwaiter.entity.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vanilla.ownwaiter.entity.BaseEntity;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Slf4j
@Getter
@Entity
@Builder
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

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    @Nullable
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "basket_id")
    @Nullable
    private Basket basket;

    @OneToMany(mappedBy = "user", targetEntity = Order.class)
    private List<Order> orders;

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

    public User(String username, String email, String password, UserSex sex, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.role = role;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

}
