package writer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import writer.domain.Inspiration;

import java.math.BigInteger;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */
public interface InspirationRepository extends MongoRepository<Inspiration, Integer> {

    public Inspiration findInspirationById(BigInteger id);

}
