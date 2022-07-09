package vanilla.ownwaiter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.repository.RestaurantRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class OwnWaiterApplication {

	private final RestaurantRepository restaurantRepository;

	@PostConstruct
	void init() {
		Restaurant res1 = Restaurant.builder().name("김밥천국1").location("도곡점").build();
		Restaurant res2 = Restaurant.builder().name("김밥천국2").location("매봉점").build();
		restaurantRepository.save(res1); restaurantRepository.save(res2);
	}

	public static void main(String[] args) {
		SpringApplication.run(OwnWaiterApplication.class, args);


	}

}
