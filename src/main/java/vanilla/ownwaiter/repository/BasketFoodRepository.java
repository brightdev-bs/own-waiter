package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.basket.Basket;
import vanilla.ownwaiter.entity.basket.BasketFood;

import java.util.List;

@Repository
public interface BasketFoodRepository extends JpaRepository<BasketFood, Long> {

    @Query("select bfood from BasketFood bfood where bfood.basket = :basket and bfood.submitFlag = :submitFlag")
    List<BasketFood> findUnsubmittedFoods(@Param("basket") Basket basket, @Param("submitFlag") String submitFlag);

}
