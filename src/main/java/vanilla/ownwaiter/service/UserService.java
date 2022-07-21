package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.dto.JoinForm;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    @Transactional
    public User save(JoinForm joinForm) {
        User user = persistUser(joinForm.toEntity(joinForm));
        return user;
    }

    private User persistUser(User user) {
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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
