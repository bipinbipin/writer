package writer.services.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import writer.domain.User;
import writer.services.UserService;

import java.util.List;

/**
 * Created by Arianna.Raduechel on 3/24/2017.
 */

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoDatabase database = mongoClient.getDatabase("writer");
        MongoCollection<Document> collection = database.getCollection("user");
        Document document = collection.find(Filters.eq("username", username)).first();
        if (document != null) {
            User user = userService.findUserByUsername(username);
            return user;
        }
        return null;
    }
}
