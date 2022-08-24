package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vanilla.ownwaiter.entity.Order;
import vanilla.ownwaiter.entity.restaurant.Restaurant;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.restaurant = :restaurant and o.completeFlag = :flag")
    List<Order> findUncompletedOrder(@Param("restaurant")Restaurant restaurant, @Param("flag") String flag);
}
