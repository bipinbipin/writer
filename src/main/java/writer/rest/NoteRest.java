package writer.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writer.domain.Note;
import writer.domain.User;
import writer.services.NoteService;
import writer.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arianna.Raduechel on 3/23/2017.
 */

@RestController
@CrossOrigin
@EnableWebSecurity
@RequestMapping("/api/note")
public class NoteRest {
    private Logger log = Logger.getLogger(NoteRest.class);

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    //region GET ALL
    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET)
    public Iterable<Note> getAllNotesByUser(@PathVariable BigInteger userId) {
        return noteService.getAllNotesFilterByUserOrderByCreatedAsc(userId);
    }
    //endregion

    //region GET INDIVIDUAL NOTE
    @RequestMapping(value = "individual/{userId}/{index}", method = RequestMethod.GET)
    public Note individualNoteRetrieval(@PathVariable BigInteger userId,
                                        @PathVariable Integer index) {
        return noteService.getNoteByUserIdAndNoteIndex(userId, index);
    }
    //endregion

    //region SAVE NOTE
    @RequestMapping(value = "/save/{userId}/{index}", method = RequestMethod.POST)
    public Note saveNoteByUserIdAndNoteIndex(@RequestBody Note noteFromAJAX,
                         @PathVariable BigInteger userId,
                         @PathVariable Integer index) {
        return noteService.saveNoteByUserIdAndNoteIndex(noteFromAJAX, userId, index);
    }
    //endregion

    //region DELETE NOTE
    @RequestMapping(value = "delete/{userId}/{index}", method = RequestMethod.POST)
    public void deleteNoteByUserIdAndNoteIndex(@PathVariable BigInteger userId,
                                               @PathVariable Integer index) {
        noteService.deleteNoteByUserIdAndNoteIndex(userId, index);
    }
    //endregion

    //region SEARCH TAGS
    @RequestMapping(value = "/{userId}/{keyword}", method = RequestMethod.GET)
    public Iterable<Note> getNotesFilteredByTag(@PathVariable BigInteger userId,
                                                @PathVariable String keyword) {
        return noteService.getNotesFilteredByTag(userId, keyword);
    }
    //endregion
}
