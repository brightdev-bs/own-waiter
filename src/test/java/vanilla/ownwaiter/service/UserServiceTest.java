package vanilla.ownwaiter.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.entity.user.UserRole;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private RestaurantService restaurantService;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("asd@naver.oom")
                .password("123")
                .username("kim")
                .role(UserRole.USER)
                .build();
        userService.save(user);
    }

    @AfterEach
    void clear() {
        userService.deleteAll();
    }

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

    @Test
    void fetchRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .name("김밥천국3")
                .location("도곡")
                .description("hi")
                .build();
        restaurantService.save(restaurant);
        User tester = User.builder()
                .email("saveTest@naver.oom")
                .password("save")
                .username("test")
                .restaurant(restaurant)
                .build();
        userRepository.save(tester);
        userRepository.flush();
        User fetchUser = userRepository.findRestaurantFetchJoin(tester.getId());
        assertEquals(fetchUser.getRestaurant().getName(), restaurant.getName());
    }
}