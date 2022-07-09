package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.repository.RestaurantRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant) {
        // location이랑 이름을 합쳐서 저장하게 끔 하는게 좋을 것 같음.
        // bc 김밥천국 도곡점, 김밥천국 매봉점, 이런식으로 하기 위함.
        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Long id) {
        Optional<Restaurant> findRes = restaurantRepository.findById(id);
        findRes.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식점입니다."));
        return findRes.get();
    }

    public Restaurant findByName(String name) {
        Optional<Restaurant> findRes = restaurantRepository.findByName(name);
        findRes.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식점입니다."));
        return findRes.get();
    }

    public List<Restaurant> likeByKeyword(String keyword) {
        return restaurantRepository.likeByKeyword(keyword);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void deleteAll() {
        restaurantRepository.deleteAll();
    }
}
