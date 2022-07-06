package vanilla.ownwaiter.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vanilla.ownwaiter.domain.user.SiteUser;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public Long save(SiteUser siteUser) {
        em.persist(siteUser);
        return siteUser.getId();
    }

    public SiteUser findById(Long id) {
        return em.find(SiteUser.class, id);
    }

    public Optional<SiteUser> findByEmail(String email) {
        return getAllUser().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public List<SiteUser> getAllUser() {
        List<SiteUser> select_u_from_siteUser_u = em.createQuery("select u from SiteUser u").getResultList();
        return select_u_from_siteUser_u;
    }

}
