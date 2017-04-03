package writer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import writer.domain.Note;
import writer.services.NoteService;
import writer.services.UserService;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@Controller
@EnableWebSecurity
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    private NoteService noteService;

    @RequestMapping("/")
    public String index(Model model) {
        //find out if someone is logged on
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //if someone is logged in, fill in .jsp info
        if (auth.getName() != "anonymousUser") {
            writer.domain.User user = userService.findUserByUsername(auth.getName());
            model.addAttribute("userUsername", user.getUsername());
            model.addAttribute("userId", user.getId());
        }

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }

        modelAndView.setViewName("index");
        return modelAndView;
    }
}
