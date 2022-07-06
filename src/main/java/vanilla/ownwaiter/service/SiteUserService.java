package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.domain.user.SiteUser;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.Optional;

@Slf4j

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final UserRepository userRepository;

    public Long save(SiteUser siteUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        siteUser.setPassword(passwordEncoder.encode(siteUser.getPassword()));
        userRepository.save(siteUser);
        return siteUser.getId();
    }
    public SiteUser findByEmail(String email) {

        Optional<SiteUser> findUser = userRepository.findByEmail(email);

        if(findUser.isEmpty()) {
            return null;
        }

        return findUser.get();
    }
}
