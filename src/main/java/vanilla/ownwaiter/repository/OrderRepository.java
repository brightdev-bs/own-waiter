package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vanilla.ownwaiter.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
