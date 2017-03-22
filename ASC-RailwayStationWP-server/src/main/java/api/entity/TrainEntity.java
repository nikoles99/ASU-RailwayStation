package api.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "trains")
public class TrainEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "train", cascade = {CascadeType.ALL})
    private List<CarriageEntity> carriages;

    @OneToMany(mappedBy = "train", cascade = {CascadeType.ALL})
    private List<ScheduleEntity> schedules;

    public TrainEntity() {
    }

    public List<CarriageEntity> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<CarriageEntity> carriages) {
        for (CarriageEntity carriage : carriages) {
            carriage.setTrain(this);
        }
        this.carriages = carriages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        for (ScheduleEntity scheduleEntity : schedules) {
            scheduleEntity.setTrain(this);
        }
        Collections.sort(schedules);
        this.schedules = schedules;
    }
}
