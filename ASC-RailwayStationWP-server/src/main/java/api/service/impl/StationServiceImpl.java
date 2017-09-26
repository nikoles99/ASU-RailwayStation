package api.service.impl;

import api.convertors.StationConverter;
import api.dao.StationDao;
import api.entity.StationEntity;
import api.exception.StationException;
import api.model.StationBean;
import api.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@Service
public class StationServiceImpl implements StationService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StationDao stationDao;

    @Autowired
    private StationConverter stationConverter;

    @Override
    public void add(StationBean station) throws StationException {
        StationEntity stationEntity = stationConverter.toEntity(station);
        stationDao.add(stationEntity);
    }

    @Override
    public List<StationBean> getAllStations() {
        List<StationEntity> entities = stationDao.getAllStations();
        List<StationBean> beans = stationConverter.toBeanCollection(entities);
        return beans;
    }

    @Override
    public void delete(StationBean station) {
        StationEntity stationEntity = stationConverter.toEntity(station);
        stationDao.delete(stationEntity);
    }

    @Override
    public void update(StationBean station) {
        StationEntity stationEntity = stationConverter.toEntity(station);
        stationDao.update(stationEntity);
    }

    @Override
    public StationBean getById(Integer id) {
        StationEntity stationEntity = stationDao.getById(id);
        return stationConverter.toBean(stationEntity);
    }

    @Override
    public List<StationBean> getByName(String name) {
        List<StationEntity> stationEntity = stationDao.getByName(name);
        return stationConverter.toBeanCollection(stationEntity);
    }
}
