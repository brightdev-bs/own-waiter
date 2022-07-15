package vanilla.ownwaiter.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Basket;
import vanilla.ownwaiter.entity.BasketFood;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketFoodRepository;
import vanilla.ownwaiter.repository.BasketRepository;
import vanilla.ownwaiter.repository.FoodRepository;
import vanilla.ownwaiter.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BasketFoodService {

    private final FoodRepository foodRepository;
    private final BasketRepository basketRepository;
    private final BasketFoodRepository basketFoodRepository;

    @Transactional
    public void save(String foodId, Authentication auth) {
        Food food = foodRepository.findById(Long.parseLong(foodId)).orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식입니다."));

        Basket basket = getBasket(auth);

        BasketFood basketFood = createBasketFood();
        basketFood.setFood(food);
        basketFood.setBasket(basket);
    }

    public Basket getBasket(Authentication auth) {
        User user =  (User) auth.getPrincipal();
        return basketRepository.findByUserId(user.getId()).orElseThrow(() -> new NoSuchElementException("없는 장바구니입니다."));
    }

    private BasketFood createBasketFood() {
        BasketFood basketFood = new BasketFood();
        basketFoodRepository.save(basketFood);
        return basketFood;
    }
}
