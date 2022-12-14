package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.user.User;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u left join fetch u.restaurant where u.id =:id")
    User findRestaurantFetchJoin(@Param("id")  Long id);

    @Query("select u from User u left join fetch u.searchHistories where u.id =:id")
    List<String> findSearchHistoryByFetchJoin(@Param("id") Long id);
}
