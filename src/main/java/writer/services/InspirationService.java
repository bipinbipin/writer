package writer.services;

import writer.domain.Inspiration;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

public interface InspirationService {

    Iterable<Inspiration> listAllInspirations();

    Inspiration getInspirationById(Integer id);

    Inspiration saveInspiration(Inspiration inspiration);

    Iterable<Inspiration> saveInspirationIterable(Iterable<Inspiration> inspirationIterable);

    void deleteInspiration(Integer id);

}
