package vanilla.ownwaiter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.food.Food;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void findById() {

        Food food = Food.builder().name("김밥").price(5000).build();
        itemService.save(food);

        Food findFood = itemService.findById(food.getId());
        assertEquals(findFood.getId(), food.getId());
        assertEquals(findFood.getName(), food.getName());
    }
}