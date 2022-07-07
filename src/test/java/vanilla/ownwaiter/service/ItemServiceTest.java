package vanilla.ownwaiter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.Item;
import vanilla.ownwaiter.repository.ItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void findById() {

        Item item = Item.builder().name("김밥").price(5000).build();
        itemService.save(item);

        Item findItem = itemService.findById(item.getId());
        assertEquals(findItem.getId(), item.getId());
        assertEquals(findItem.getName(), item.getName());
    }
}