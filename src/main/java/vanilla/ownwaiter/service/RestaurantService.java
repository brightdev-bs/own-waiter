package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.repository.RestaurantRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
