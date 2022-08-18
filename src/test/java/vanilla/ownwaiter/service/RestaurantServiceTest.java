package vanilla.ownwaiter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.restaurant.Restaurant;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;
    private Restaurant res;

    @BeforeEach
    void init() {
        restaurantService.deleteAll();
        res = Restaurant.builder().name("김밥천국").location("매봉").build();
        restaurantService.save(res);
    }

    @Test
    void save() {
        Restaurant newRes = Restaurant.builder().name("뉴욕시장").location("서울").build();

        Restaurant saveRes = restaurantService.save(newRes);
        assertEquals(saveRes.getName(), newRes.getName());
        assertEquals(saveRes.getLocation(), newRes.getLocation());
    }

    @Test
    void findById() {
        Restaurant findRes = restaurantService.findById(res.getId());
        assertEquals(findRes.getId(), res.getId());
        assertEquals(findRes.getName(), res.getName());
    }

    @Test
    void failFindById() {
        assertThrows(NoSuchElementException.class, () -> restaurantService.findById(0L));
    }

    @Test
    void findByName() {
        Restaurant findRes = restaurantService.findByName(res.getName());
        assertEquals(findRes.getName(), res.getName());
        assertEquals(findRes.getLocation(), res.getLocation());
    }

    @Test
    void failFindByName() {
        assertThrows(NoSuchElementException.class, () -> restaurantService.findByName("asd123"));
    }

    @Test
    void findAll() {
        Restaurant res1 = Restaurant.builder().name("서울야시장").location("서울").build();
        restaurantService.save(res1);

        Restaurant res2 = Restaurant.builder().name("부산야시장").location("부산").build();
        restaurantService.save(res2);

        List<Restaurant> restaurants = restaurantService.findAll();
        assertEquals(restaurants.size(), 3);
    }

    @Test
    void likeByKeyword() {

    }



}