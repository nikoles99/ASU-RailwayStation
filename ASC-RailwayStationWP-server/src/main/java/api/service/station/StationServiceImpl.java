package api.service.station;

import api.convertors.StationConverter;
import api.dao.station.StationDao;
import api.entity.StationEntity;
import api.model.StationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nikita on 27.02.17.
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationDao stationDao;

    @Autowired
    private StationConverter stationConverter;

    @Override
    public void addStation(StationBean stationBean) {
        StationEntity stationEntity = stationConverter.convertToEntity(stationBean);
        stationDao.addStation(stationEntity);
    }
}
