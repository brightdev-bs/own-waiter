package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.basket.BasketFood;

import java.util.List;

@Repository
public interface BasketFoodRepository extends JpaRepository<BasketFood, Long> {
}
