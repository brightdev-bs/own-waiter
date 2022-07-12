package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.food.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>  { }
