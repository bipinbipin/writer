package writer.services;

import writer.domain.Note;
import writer.domain.User;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

public interface NoteService {

    Iterable<Note> listAllNotes();

    Iterable<Note> getAllNotesFilterByUserOrderByCreatedAsc(BigInteger userId);

    Note getNoteById(Integer id);

    Note getNoteById(BigInteger id);

    Note getNoteByUserIdAndNoteIndex(BigInteger userId, Integer index);

    Note saveNoteByUserIdAndNoteIndex(Note noteToSave, BigInteger userId, Integer index);

    Note saveNote(Note note);

    void deleteNoteByUserIdAndNoteIndex(BigInteger userId, Integer index);

    Iterable<Note> saveNoteIterable(Iterable<Note> noteIterable);

    Iterable<Note> getNotesFilteredByTag(BigInteger userId, String keyword);
}
