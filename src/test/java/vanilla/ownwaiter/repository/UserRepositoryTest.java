package vanilla.ownwaiter.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vanilla.ownwaiter.entity.user.User;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        User user = User.builder()
                .email("asd@naver.com")
                .password("123")
                .username("kim")
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        User findUser = userRepository.findByEmail("asd@naver.com").orElseThrow(() -> new NoSuchElementException("에러"));
        assertEquals(findUser.getEmail(),"asd@naver.com");
        assertEquals(findUser.getUsername(), "kim");
    }

    @Test
    void findByUsername() {
        User user = userRepository.findByUsername("kim").orElseThrow(() -> new NoSuchElementException("에러"));
        assertEquals(user.getUsername(), "kim");
    }
}