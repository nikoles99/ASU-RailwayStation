package api.convertors;

import api.entity.StationEntity;
import api.model.StationBean;
import org.springframework.stereotype.Component;

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
}
