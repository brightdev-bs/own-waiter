package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.domain.user.SiteUser;
import vanilla.ownwaiter.domain.user.SiteUserRole;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final SiteUserService siteUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SiteUser siteUser = getSiteUser(email);
        siteUser.setAuthorities(getGrantedAuthorities(siteUser));
        return siteUser;
    }

    private SiteUser getSiteUser(String email) {
        SiteUser findUser = siteUserService.findByEmail(email);
        if (findUser == null) {
            throw new UsernameNotFoundException("존재 하지 않는 유저입니다.");
        }
        return findUser;
    }

    private List<GrantedAuthority> getGrantedAuthorities(SiteUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole().equals(SiteUserRole.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return authorities;
    }
}
