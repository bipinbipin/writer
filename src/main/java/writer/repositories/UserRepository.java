package writer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import writer.domain.User;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

public interface UserRepository extends MongoRepository<User, Integer> {

    public User findUserByUsername(String username);

    public User findUserById(BigInteger id);
}
