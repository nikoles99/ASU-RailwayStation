package api.service.station;

import api.exception.StationException;
import api.model.StationBean;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
public interface StationService {

    void addStation(StationBean stationBean) throws StationException;

    List<StationBean> getAllStations();
}
