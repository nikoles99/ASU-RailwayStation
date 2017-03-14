package api.convertors;

import api.entity.StationEntity;
import api.model.StationBean;
import org.springframework.stereotype.Component;

/**
 * Created by nikita on 27.02.17.
 */
@Component
public class StationConverter extends AbstractConvertor<StationBean, StationEntity> {

    @Override
    public StationBean convertToBean(StationEntity stationEntity) {
        StationBean stationBean = new StationBean();
        stationBean.setName(stationEntity.getName());
        stationBean.setId(stationEntity.getId());
        stationBean.setSchedules(stationEntity.getSchedules());
        return stationBean;
    }

    @Override
    public StationEntity convertToEntity(StationBean stationBean) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName(stationBean.getName());
        stationEntity.setId(stationBean.getId());
        stationEntity.setSchedules(stationBean.getSchedules());
        return stationEntity;
    }
}
