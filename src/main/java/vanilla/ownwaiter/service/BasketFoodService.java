package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketFoodRepository;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.FoodRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasketFoodService {

    private final FoodRepository foodRepository;
    private final BasketRepository basketRepository;
    private final BasketFoodRepository basketFoodRepository;

    @Transactional
    public void save( @AuthenticationPrincipal User user, String foodId) {
        Food food = foodRepository.findById(Long.parseLong(foodId)).orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식입니다."));

        Basket basket = getBasket(user);

        BasketFood basketFood = createBasketFood();
        basketFood.setFood(food);
        basketFood.setBasket(basket);
    }

    public Basket getBasket(@AuthenticationPrincipal User user) {
        return basketRepository.findByUserId(user.getId()).orElseThrow(() -> new NoSuchElementException("없는 장바구니입니다."));
    }

    public List<BasketFood> getBasketFood(@AuthenticationPrincipal User user) {
        List<BasketFood> basketFoods = basketFoodRepository.findUnsubmittedFoods(getBasket(user), "N");
        return basketFoods;
    }

    private BasketFood createBasketFood() {
        BasketFood basketFood = new BasketFood();
        basketFoodRepository.save(basketFood);
        return basketFood;
    }
}
