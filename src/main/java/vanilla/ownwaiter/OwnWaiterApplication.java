package vanilla.ownwaiter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.user.User;
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
	private final UserService userService;

	@PostConstruct
	void init() {
		Restaurant res1 = Restaurant.builder().name("김밥천국1").location("도곡점").build();
		Restaurant res2 = Restaurant.builder().name("김밥천국2").location("매봉점").build();
		restaurantRepository.save(res1); restaurantRepository.save(res2);

		User admin = User.builder().username("kim").email("manager@gmail.com").password("123").authorities(new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ADMIN")))).build();
		User user = User.builder().username("lee").email("user@gmail.com").password("123").authorities(new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("USER")))).build();
		userService.save(admin);
		userService.save(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(OwnWaiterApplication.class, args);


	}

}
