package writer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import writer.domain.User;
import writer.repositories.UserRepository;
import writer.services.UserService;
import java.util.List;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserById(BigInteger id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User saveUser(User userFromAJAX) {
        User userToSave = new User();

        userToSave.setFirstName(userFromAJAX.getFirstName());
        userToSave.setLastName(userFromAJAX.getLastName());
        userToSave.setEmailAddress(userFromAJAX.getEmailAddress());
        userToSave.setUsername(userFromAJAX.getUsername());
        userToSave.setPassword(userFromAJAX.getPassword());
        userToSave.setGrantedAuthorities(AuthorityUtils.createAuthorityList(new String[] {"user"}));

        return userRepository.save(userToSave);
    }

    @Override
    public Iterable<User> saveUserIterable(Iterable<User> userIterable) {
        return userRepository.save(userIterable);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}
