package vanilla.ownwaiter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        User user = User.builder()
                .email("asd@naver.com")
                .password("123")
                .username("kim")
                .build();
        userRepository.save(user);

        User findUser = userRepository.findByEmail("asd@naver.com");
        assertEquals(findUser.getUsername(), "kim");
    }
}