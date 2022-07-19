package vanilla.ownwaiter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.repository.RestaurantRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final QrCodeService qrCodeService;
    private final String DEFAULT_IMG_URL = "http://placeimg.com/100/100/nature";

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant saveWithQr(Restaurant restaurant) {
        String uploadUrl = qrCodeService.generateAndUpload(restaurant.getId());
        restaurant.setQrCodeUrl(uploadUrl);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant setImg(Restaurant restaurant, String imgUrl){
        if(imgUrl == null)
            restaurant.setProfileImgUrl(DEFAULT_IMG_URL);

        if(imgUrl != null)
            restaurant.setProfileImgUrl(imgUrl);

        restaurantRepository.save(restaurant);
        return restaurant;
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
