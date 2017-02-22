package api.dao.place;

import api.entity.PlaceEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface PlaceDao {

    void addPlace(PlaceEntity placeEntity);

    PlaceEntity getPlace(Integer id);

    void updatePlace(PlaceEntity placeEntity);

    void removePlace(PlaceEntity placeEntity);
}
