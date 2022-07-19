package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Restaurant;
import vanilla.ownwaiter.entity.food.Food;
import vanilla.ownwaiter.entity.user.User;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@Param("email") String email);

    @Query("select u from User u left join fetch u.restaurant where u.id =:id")
    User findRestaurantFetchJoin(@Param("id")  Long id);

    @Query("select u, r from User u, Restaurant r left join fetch r.foods")
    List<Food> findRestaurantFoodFetchJoin();
}
