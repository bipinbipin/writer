package writer.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "writer.repositories")
@EntityScan(basePackages = {"writer.domain"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
