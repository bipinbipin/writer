package writer.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String title;
    private String body;
    private boolean visible;
    private String created;
    private ArrayList<String> tagList;
    /*
    There’s no need to use something like @OneToMany because the mapping framework sees that you want a one-to-many relationship because there is a List of objects.
    When the object is stored in MongoDB, there will be a list of DBRefs rather than the Note objects themselves.
     */
    @DBRef
    private User user;

    //region CONSTRUCTORS
    public Note() {}
    public Note(String title, String body, boolean visible, Date created, ArrayList<String> tagList, User user) {
        this.title = title;
        this.body = body;
        this.visible = visible;
        this.created = created.toString();
        this.tagList = tagList;
        this.user = user;
    }
    //endregion
}