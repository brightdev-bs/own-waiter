package vanilla.ownwaiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.entity.user.User;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@Param("email") String email);
}
