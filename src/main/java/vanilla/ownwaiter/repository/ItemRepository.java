package vanilla.ownwaiter.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.Item;

import javax.persistence.EntityManager;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>  { }
