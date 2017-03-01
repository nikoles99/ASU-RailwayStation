package api.model;

import api.entity.TrainScheduleEntity;

import java.util.List;

/**
 * Created by nolesuk on 01-Mar-17.
 */
public class StationBean {
    private String name;
    private List<TrainScheduleEntity> schedules;

    public StationBean() {
    }

    public void setSchedules(List<TrainScheduleEntity> schedules) {
        this.schedules = schedules;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<TrainScheduleEntity> getSchedules() {
        return schedules;
    }
}
