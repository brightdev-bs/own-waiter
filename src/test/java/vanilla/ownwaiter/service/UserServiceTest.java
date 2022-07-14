package vanilla.ownwaiter.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.entity.user.UserRole;
import vanilla.ownwaiter.repository.BasketRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BasketRepository basketRepository;
    private User user;

//    @BeforeEach
//    void setUp() {
//        user = User.builder()
//                .email("asd@naver.oom")
//                .password("123")
//                .username("kim")
//                .role(UserRole.USER)
//                .build();
//        userService.save(user);
//    }
//
//    @AfterEach
//    void clear() {
//        userService.deleteAll();
//    }

    @Test
    void save() {
        Basket basket = new Basket();
        basketRepository.save(basket);
        User tester = User.builder()
                .email("saveTest@naver.oom")
                .password("save")
                .username("test")
                .basket(basket)
                .build();
        User saveUser = userService.save(tester);
        assertEquals(tester.getId(), saveUser.getId());
        assertEquals(tester.getEmail(), saveUser.getEmail());
        assertEquals(saveUser.getBasket().getId(), basket.getId());
    }

    @Test
    void findById() {
        Optional<User> findUser = userService.findById(user.getId());
        if(findUser.isEmpty()) {
            fail("유저를 찾을 수 없습니다.");
            return;
        }

        assertEquals(findUser.get().getEmail(), user.getEmail());
        assertEquals(findUser.get().getId(), user.getId());
    }

    @Test
    void findByEmail() {
        User findUser = userService.findByEmail("asd@naver.oom");
        assertEquals(findUser.getEmail(), user.getEmail());
        assertEquals(findUser.getPassword(), user.getPassword());
    }

}