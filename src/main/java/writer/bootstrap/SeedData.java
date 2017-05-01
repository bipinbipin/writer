package writer.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import writer.domain.Inspiration;
import writer.domain.Note;
import writer.domain.User;
import writer.repositories.UserRepository;
import writer.services.InspirationService;
import writer.services.NoteService;
import writer.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

//changes for test 2
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    //region @Autowired
    @Autowired private InspirationService inspirationService;
    @Autowired private NoteService noteService;
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    //endregion

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //generateData();
    }

    private void generateData() {
        userRepository.deleteAll();

        String[] authorities = {"user", "admin"};

        User u1 = new User("anarad@gmail.com", "anarad", "password", "Ana", "Rad", authorities);

        Note n1 = new Note("Passage", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", false, new Date(), new ArrayList<>() ,u1);

        List<Note> nl1 = new ArrayList<>();
        nl1.add(n1);

        Inspiration i1  = new Inspiration("Writing still used from the 1800s", nl1);

        userService.saveUser(u1);
        noteService.saveNote(n1);
        inspirationService.saveInspiration(i1);
    }
}
