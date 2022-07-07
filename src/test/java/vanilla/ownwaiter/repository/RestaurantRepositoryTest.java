package vanilla.ownwaiter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
        restaurantRepository.deleteAll();
        res = Restaurant.builder()
                .name("김밥천국")
                .location("서울 23")
                .build();
        restaurantRepository.save(res);
    }

    @Test
    void findById() {
        Restaurant result = restaurantRepository.getById(res.getId());
        assertEquals(result.getId(), res.getId());
    }

    @Test
    void findByName() {

        Optional<Restaurant> findRes = restaurantRepository.findByName("김밥천국");
        findRes.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식점입니다."));

        assertEquals(findRes.get().getName(), res.getName());
    }
}