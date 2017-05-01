package writer;

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import spock.lang.Specification
import writer.domain.User;
import writer.services.UserService;

/**
 * Created by Arianna.Raduechel on 5/1/2017.
 */

public class CustomUserDetailsServiceTests extends Specification {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private UserService userService;

    def "Find user in MongoDB via username"() {
        when:
        MongoDatabase database = Mock();
        MongoCollection<javax.swing.text.Document> collection = Mock();

        then:
        println("Collection: " + collection)
        println("Database: " + database)
        println(collection.getMetaPropertyValues().toListString())
        collection != null
        database != null
    }


}
