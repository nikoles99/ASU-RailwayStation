package api.dao.station;

import api.entity.StationEntity;
import api.exception.StationException;
import api.model.SimpleResponseBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional
public interface StationDao {

    void addStation(StationEntity stationEntity) throws StationException;

    List<StationEntity> getStations(String name);

    void updateStation(StationEntity stationEntity);

    List<StationEntity> getAllStations();

    void deleteStation(StationEntity stationEntity);
}
