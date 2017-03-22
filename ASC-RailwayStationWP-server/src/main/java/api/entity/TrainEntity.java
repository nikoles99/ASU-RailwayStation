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

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    List<CarriageEntity> carriages;

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    List<ScheduleEntity> scheduleEntities;

    public TrainEntity() {
    }

    public List<CarriageEntity> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<CarriageEntity> carriages) {
        for (CarriageEntity carriage : carriages) {
            carriage.setTrainEntity(this);
        }
        this.carriages = carriages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleEntity> getScheduleEntities() {
        return scheduleEntities;
    }

    public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            scheduleEntity.setTrainEntity(this);
        }
        Collections.sort(scheduleEntities);
        this.scheduleEntities = scheduleEntities;
    }
}
