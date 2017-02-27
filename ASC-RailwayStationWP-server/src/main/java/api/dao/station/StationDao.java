package api.dao.station;

import api.entity.StationEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional
public interface StationDao {

    void addStation(StationEntity stationEntity);

    StationEntity getStation(String name);

    void updateStation(StationEntity stationEntity);

    void removeStation(StationEntity stationEntity);
}
