package vanilla.ownwaiter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;
import vanilla.ownwaiter.repository.FoodRepository;
import vanilla.ownwaiter.repository.RestaurantRepository;
import vanilla.ownwaiter.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class OwnWaiterApplication {

	private final RestaurantRepository restaurantRepository;
	private final FoodRepository foodRepository;

	@PostConstruct
	void init() {
		Restaurant res1 = Restaurant.builder().name("김밥천국1").location("도곡점").description("김밥천국입니다.").profileImgUrl("https://waiterbucket.s3.ap-northeast-2.amazonaws.com/qr/d3798585-f3dc-44c7-b0b3-99bff1f4019b.png").build();
		Restaurant res2 = Restaurant.builder().name("김밥천국2").location("매봉점").description("김밥천국입니다.").profileImgUrl("https://waiterbucket.s3.ap-northeast-2.amazonaws.com/qr/d3798585-f3dc-44c7-b0b3-99bff1f4019b.png").build();
		restaurantRepository.save(res1); restaurantRepository.save(res2);

		Food food1 = Food.builder().name("음식1").description("김밥입니다").price(1000).imgUrl("https://waiterbucket.s3.ap-northeast-2.amazonaws.com/qr/d3798585-f3dc-44c7-b0b3-99bff1f4019b.png").restaurant(res1).build();
		Food food2 = Food.builder().name("음식2").description("김밥2입니다").price(1500).imgUrl("https://waiterbucket.s3.ap-northeast-2.amazonaws.com/qr/d3798585-f3dc-44c7-b0b3-99bff1f4019b.png").restaurant(res1).build();
		foodRepository.save(food1); foodRepository.save(food2);
	}

	public static void main(String[] args) {
		SpringApplication.run(OwnWaiterApplication.class, args);
	}

}
