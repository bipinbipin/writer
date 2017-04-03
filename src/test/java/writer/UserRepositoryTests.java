package writer;

import static org.assertj.core.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import writer.domain.User;
import writer.repositories.UserRepository;
import writer.services.UserService;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    User u1, u2, u3;

    @Before
    public void setUp() {
        userRepository.deleteAll();

        String[] authorities = {"user", "admin"};

        u1 = userRepository.save(new User("anarad@gmail.com", "anarad", "password", "ana", "rad", authorities));
        u2 = userRepository.save(new User("acat@gmail.com", "aaron", "password", "a", "cat", authorities));
        u3 = userRepository.save(new User("jojo@gmail.com", "jos", "password", "josie", "run", authorities));
    }

    @Test
    public void setIdOnSave() {
        userRepository.save(u1);

        User u1Retrieved = userRepository.findUserByUsername("anarad");
        assertThat(u1Retrieved.getId()).isNotNull();
    }

    @Test
    public void findAll() {
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);

        List<User> userList = userRepository.findAll();

        for(User user : userList) {
            System.out.println(user.toString());
        }

        assertThat(userRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void saveAndReturnId() {
        BigInteger id = userRepository.save(u1).getId();
        System.out.println(id);
        assertThat(id).isNotNull();
    }

    @Test
    public void updateByBigIntegerId() {
        BigInteger id = userRepository.save(u2).getId();

        User updatedUser = userRepository.findUserById(id);
        updatedUser.setPassword("newPassword");
        userRepository.save(updatedUser);

        assertThat(updatedUser.getPassword()).isEqualTo("newPassword");
    }
}
