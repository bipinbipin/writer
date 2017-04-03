package writer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import writer.domain.Note;
import writer.domain.User;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */
public interface NoteRepository extends MongoRepository<Note, Integer> {

    public Note findNoteById(BigInteger id);

    public Iterable<Note> getAllNotesFilterByUserOrderByCreatedAsc(User user);
}
