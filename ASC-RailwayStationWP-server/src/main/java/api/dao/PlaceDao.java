package api.dao;

import api.entity.PlaceEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface PlaceDao {

    void add(PlaceEntity place);

    PlaceEntity get(Integer id);

    void update(PlaceEntity place);

    void remove(PlaceEntity place);
}
