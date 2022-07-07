package vanilla.ownwaiter.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.Restaurant;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantRepository {

    private final EntityManager em;

    @Transactional
    public Long save(Restaurant restaurant) {
        em.persist(restaurant);
        return restaurant.getId();
    }

    public Restaurant findById(Long id) {
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class)
                .getResultList();
    }

    public List<Restaurant> findByName(String name) {
        return em.createQuery("select r from Restaurant r where r.name = :name", Restaurant.class)
                .setParameter("name", name)
                .getResultList();
    }
}
