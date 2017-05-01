package writer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import writer.domain.Note;
import writer.domain.User;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

//Equivalent of CrudRepository<> in Spring Data MongoDB
public interface NoteRepository extends MongoRepository<Note, Integer> {

    public Note findNoteById(BigInteger id); //Made custom find by id that could use BigInteger instead of int or Integer

    public Iterable<Note> getAllNotesFilterByUserOrderByCreatedAsc(User user); //Didn't write logic for, used repo keywords
}
