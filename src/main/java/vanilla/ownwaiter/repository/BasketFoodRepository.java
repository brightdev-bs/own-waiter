package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.BasketFood;

@Repository
public interface BasketFoodRepository extends JpaRepository<BasketFood, Long> {


}
