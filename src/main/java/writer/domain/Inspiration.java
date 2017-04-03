package writer.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;

@Document
@Data
public class Inspiration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String body;

    @DBRef
    private List<Note> noteList;

    //region CONSTRUCTORS
    public Inspiration() {}
    public Inspiration(String body, List<Note> notes) {
        this.body = body;
        this.noteList = notes;
    }
    //endregion
}