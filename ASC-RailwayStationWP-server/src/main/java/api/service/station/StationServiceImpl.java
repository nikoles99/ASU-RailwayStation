package api.service.station;

import api.convertors.StationConverter;
import api.dao.station.StationDao;
import api.entity.StationEntity;
import api.exception.StationException;
import api.model.StationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    StationDao stationDao;

    @Autowired
    private StationConverter stationConverter;

    @Override
    public void addStation(StationBean stationBean) throws StationException {
        StationEntity stationEntity = stationConverter.convertToEntity(stationBean);
        stationDao.addStation(stationEntity);
    }

    @Override
    public List<StationBean> getAllStations() {
        List<StationEntity> entities = stationDao.getAllStations();
        List<StationBean> beans = stationConverter.convertToBeanCollection(entities);
        return beans;
    }

    @Override
    public void deleteStation(StationBean stationBean) {
        StationEntity stationEntity = stationConverter.convertToEntity(stationBean);
        stationDao.deleteStation(stationEntity);
    }

    @Override
    public void updateStation(StationBean stationBean) {
        StationEntity stationEntity = stationConverter.convertToEntity(stationBean);
        stationDao.updateStation(stationEntity);
    }
}
