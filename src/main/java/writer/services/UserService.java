package writer.services;

import writer.domain.User;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

public interface UserService {

    Iterable<User> listAllUsers();

    User getUserById(int id);

    User findUserById(BigInteger id);

    public User findUserByUsername(String username);

    User saveUser(User user);

    Iterable<User> saveUserIterable(Iterable<User> userIterable);

    void deleteUser(Integer id);
}
