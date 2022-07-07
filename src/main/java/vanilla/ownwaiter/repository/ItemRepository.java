package vanilla.ownwaiter.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vanilla.ownwaiter.entity.Item;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }
}
