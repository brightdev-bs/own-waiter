package vanilla.ownwaiter.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

    @Query("select r from Restaurant r where r.name like %:keyword%")
    List<Restaurant> likeByKeyword(@Param("keyword") String keyword);
}
