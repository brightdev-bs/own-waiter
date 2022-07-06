package vanilla.ownwaiter.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Slf4j
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class SiteUser implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private SiteUserSex sex;

    @Enumerated(EnumType.STRING)
    private SiteUserRole role;

    @Transient
    private Collection<GrantedAuthority> authorities;

    //Todo : froxy 배우고 다시 살릴 것.
//    @OneToMany(cascade = CascadeType.ALL)
//    List<Basket> basketList = new ArrayList<>();

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

    public SiteUser(String username, String email, String password, SiteUserSex sex, SiteUserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.role = role;
    }
}
