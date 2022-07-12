package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.repository.FoodRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final FoodRepository foodRepository;

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    public Food findById(Long id) {
        Optional<Food> findItem = foodRepository.findById(id);
        findItem.orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이템입니다."));
        return findItem.get();
    }
}
