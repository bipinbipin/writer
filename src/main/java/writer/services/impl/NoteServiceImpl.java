package writer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writer.domain.Note;
import writer.domain.User;
import writer.repositories.NoteRepository;
import writer.repositories.UserRepository;
import writer.services.NoteService;
import writer.services.UserService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<Note> listAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Iterable<Note> getAllNotesFilterByUserOrderByCreatedAsc(BigInteger userId) {
        return noteRepository.getAllNotesFilterByUserOrderByCreatedAsc(userRepository.findUserById(userId));
    }

    @Override
    public Note getNoteById(Integer id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Note getNoteById(BigInteger id) {
        return noteRepository.findNoteById(id);
    }

    @Override
    public Note getNoteByUserIdAndNoteIndex(BigInteger userId, Integer index) {
        writer.domain.User user = userRepository.findUserById(userId);
        Iterable<Note> noteIterable = noteRepository.getAllNotesFilterByUserOrderByCreatedAsc(user);
        ArrayList<Note> noteArrayList = new ArrayList<>();

        for (Note note : noteIterable) {
            noteArrayList.add(note);
        }

        return noteArrayList.get(index);
    }

    @Override
    public void deleteNoteByUserIdAndNoteIndex(BigInteger userId, Integer index) {
        Note noteToDelete = getNoteByUserIdAndNoteIndex(userId, index);
        noteRepository.delete(noteToDelete);
    }

    @Override
    public Note saveNoteByUserIdAndNoteIndex(Note noteFromAJAX, BigInteger userId, Integer index) {
        //get user
        User user = userRepository.findUserById(userId);
        Note noteToSave;

        if (noteFromAJAX.getCreated() == null || noteFromAJAX.getCreated() == "") {
            noteToSave = new Note();
            noteToSave.setCreated(new Date().toString());
        } else {
            noteToSave = getNoteByUserIdAndNoteIndex(userId, index);
        }

        //update values
        noteToSave.setTitle(noteFromAJAX.getTitle());
        noteToSave.setBody(noteFromAJAX.getBody());
        noteToSave.setVisible(noteFromAJAX.isVisible());
        noteToSave.setTagList(noteFromAJAX.getTagList());
        noteToSave.setUser(user);

        return noteRepository.save(noteToSave);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Iterable<Note> saveNoteIterable(Iterable<Note> noteIterable) {
        return noteRepository.save(noteIterable);
    }

    @Override
    public Iterable<Note> getNotesFilteredByTag(BigInteger userId, String keyword) {
        Iterable<Note> allNotes = noteRepository.getAllNotesFilterByUserOrderByCreatedAsc(userRepository.findUserById(userId));
        ArrayList<Note> notesFilteredByTag = new ArrayList<Note>();

        for(Note note : allNotes) {
            note.getTagList().stream().filter(s -> s.contains(keyword)).forEach(s -> {
                notesFilteredByTag.add(note);
            });
        }

        return notesFilteredByTag;
    }
}
