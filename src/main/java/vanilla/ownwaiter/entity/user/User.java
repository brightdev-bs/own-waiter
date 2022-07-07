package vanilla.ownwaiter.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vanilla.ownwaiter.entity.Basket;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Slf4j
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserSex sex;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Transient
    private Collection<GrantedAuthority> authorities;

    @OneToOne
    private Basket basket;

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
}
