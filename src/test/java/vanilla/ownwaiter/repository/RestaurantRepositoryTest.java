package vanilla.ownwaiter.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.Restaurant;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;
    private Restaurant res;

    @BeforeEach
    void init() {
        res = Restaurant.builder()
                .name("김밥천국")
                .location("서울 23")
                .build();
        restaurantRepository.save(res);
    }

    @AfterEach
    void clear() {
        restaurantRepository.deleteAll();
    }

    @Test
    void findById() {
        Optional<Restaurant> result = restaurantRepository.findById(res.getId());
        assertEquals(result.get().getId(), res.getId());
    }

    @Test
    void findByName() {

        Optional<Restaurant> findRes = restaurantRepository.findByName("김밥천국");
        findRes.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식점입니다."));

        assertEquals(findRes.get().getName(), res.getName());
    }
}