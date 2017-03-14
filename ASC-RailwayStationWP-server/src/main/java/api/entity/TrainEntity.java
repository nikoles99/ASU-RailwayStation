package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "trains")
public class TrainEntity extends AbstractEntity {

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    List<CarriageEntity> carriages;

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    List<TrainScheduleEntity> scheduleEntities;

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

    public List<TrainScheduleEntity> getScheduleEntities() {
        return scheduleEntities;
    }

    public void setScheduleEntities(List<TrainScheduleEntity> scheduleEntities) {
        for (TrainScheduleEntity scheduleEntity : scheduleEntities) {
            scheduleEntity.setTrainEntity(this);
        }
        this.scheduleEntities = scheduleEntities;
    }
}
