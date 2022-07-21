package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.dto.JoinForm;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    private final int MAX_SEARCH_COUNT = 4;
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

    @Transactional
    public void addSearchHistory(Long userId, String input) {
        User user = findById(userId).orElseThrow(() -> new NoSuchElementException("유저의 정보를 확인할 수 없습니다."));

        List<String> searchHistory = user.getSearchHistories();
        if(!searchHistory.contains(input)) {
            searchHistory.add(input);
        }

        log.info("searchHistory = {}", searchHistory);
        userRepository.save(user);
    }

    public User getUserByAuth(Authentication auth) {
        return (User) auth.getPrincipal();
    }
    public List<String> sortSearchHistory(Authentication auth) {
        User user = getUserByAuth(auth);
        User persistUser = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("인증되지 않은 사용자입나다."));

        List<String> searchHistories = persistUser.getSearchHistories();
        if(searchHistories.size() < MAX_SEARCH_COUNT) {
            return searchHistories;
        }

        List<String> resultList = new ArrayList<>();
        if(searchHistories.size() >= MAX_SEARCH_COUNT) {
            for(int i = 0; i < MAX_SEARCH_COUNT; i++) {
                resultList.add(searchHistories.get(MAX_SEARCH_COUNT - 1));
            }
        }

        userRepository.save(persistUser);
        return resultList;
    }
}
