package api.dao;

import api.entity.PlaceEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface PlaceDao {

    void add(PlaceEntity place);

    PlaceEntity get(Integer id);

    void update(PlaceEntity place);

    void remove(PlaceEntity place);
}
