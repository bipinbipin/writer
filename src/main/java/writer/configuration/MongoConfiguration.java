package writer.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Arianna.Raduechel on 3/24/2017.
 */

@Configuration
@Profile("customuserdetails")
public class MongoConfiguration {

    @Bean
    public MongoClient createConnection() {
        System.out.println("CREATING CONNECTION");
        return new MongoClient("araduechel-lt:27017");
    }
}
