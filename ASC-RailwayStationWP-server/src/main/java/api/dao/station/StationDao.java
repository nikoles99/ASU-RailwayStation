package api.dao.station;

import api.entity.StationEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface StationDao {

    void addStation(StationEntity stationEntity);

    StationEntity getStation(Integer id);

    void updateStation(StationEntity stationEntity);

    void removeStation(StationEntity stationEntity);
}
