package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "stations")
public class StationEntity extends AbstractEntity {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "stationEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<TrainScheduleEntity> schedules;

    public StationEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrainScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TrainScheduleEntity> schedules) {
        this.schedules = schedules;
    }
}
