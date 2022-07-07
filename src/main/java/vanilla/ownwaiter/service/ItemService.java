package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.entity.Item;
import vanilla.ownwaiter.repository.ItemRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        findItem.orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이템입니다."));
        return findItem.get();
    }
}
