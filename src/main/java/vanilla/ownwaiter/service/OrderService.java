package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.basket.BasketFood;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.BasketFoodRepository;
import vanilla.ownwaiter.repository.OrderRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketFoodRepository basketFoodRepository;

    @Transactional
    public void save(Order order, List<BasketFood> foods) {
        orderRepository.save(order);
        for (BasketFood food : foods) {
            food.setSubmitFlag("Y");
        }
        basketFoodRepository.saveAll(foods);
    }
}
