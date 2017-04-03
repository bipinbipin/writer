package writer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writer.domain.Inspiration;
import writer.repositories.InspirationRepository;
import writer.services.InspirationService;

/**
 * Created by Arianna.Raduechel on 3/20/2017.
 */

@Service
public class InspirationServiceImpl implements InspirationService {

    @Autowired
    private InspirationRepository inspirationRepository;

    @Override
    public Iterable<Inspiration> listAllInspirations() {
        return inspirationRepository.findAll();
    }

    @Override
    public Inspiration getInspirationById(Integer id) {
        return inspirationRepository.findOne(id);
    }

    @Override
    public Inspiration saveInspiration(Inspiration inspiration) {
        return inspirationRepository.save(inspiration);
    }

    @Override
    public Iterable<Inspiration> saveInspirationIterable(Iterable<Inspiration> inspirationIterable) {
        return inspirationRepository.save(inspirationIterable);
    }

    @Override
    public void deleteInspiration(Integer id) {
        inspirationRepository.delete(id);
    }
}
