package writer.rest;

//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writer.domain.User;
import writer.services.UserService;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private UserService userService;

    //region GET USER
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable BigInteger userId) {
        return userService.findUserById(userId);
    }
    //endregion

    //region SAVE USER
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User userFromAJAX) {
        return userService.saveUser(userFromAJAX);
    }
    //endregion

}
