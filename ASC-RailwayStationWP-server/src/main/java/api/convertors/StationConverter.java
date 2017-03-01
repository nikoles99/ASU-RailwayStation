package api.convertors;

import api.entity.StationEntity;
import api.model.StationBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 27.02.17.
 */
@Component
public class StationConverter {

    public StationBean convertToBean(StationEntity stationEntity) {
        StationBean stationBean = new StationBean();
        stationBean.setName(stationEntity.getName());
        stationBean.setSchedules(stationEntity.getSchedules());
        return stationBean;
    }

    public StationEntity convertToEntity(StationBean stationBean) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(stationBean.getName());
        stationEntity.setSchedules(stationBean.getSchedules());
        return stationEntity;
    }

    public List<StationEntity> convertToEntity(List<StationBean> beans) {
        List<StationEntity> entities = new ArrayList<StationEntity>();
        for (StationBean bean : beans) {
            StationEntity stationEntity = convertToEntity(bean);
            entities.add(stationEntity);
        }
        return entities;
    }

    public List<StationBean> convertToBean(List<StationEntity> entities) {
        List<StationBean> beans = new ArrayList<StationBean>();
        for (StationEntity entity : entities) {
            StationBean stationEntity = convertToBean(entity);
            beans.add(stationEntity);
        }
        return beans;
    }
}
