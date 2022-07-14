package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    @Transactional
    public User save(User user) {
        encryptPassword(user);

        if(user.getBasket() == null) {
            createBasket(user);
        }

        userRepository.save(user);
        return user;
    }

    private void encryptPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
    }

    private void createBasket(User user) {
        Basket basket = new Basket();
        basketRepository.save(basket);
        user.setBasket(basket);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
