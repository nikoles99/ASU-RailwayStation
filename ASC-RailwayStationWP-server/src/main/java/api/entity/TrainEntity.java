package api.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "trains")
public class TrainEntity extends AbstractEntity {

    @Column(name = "number")
    Integer number;

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    Set<CarriageEntity> carriages;

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    Set<TrainScheduleEntity> scheduleEntities;

    public TrainEntity() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<CarriageEntity> getCarriages() {
        return carriages;
    }

    public void setCarriages(Set<CarriageEntity> carriages) {
        this.carriages = carriages;
    }

    public Set<TrainScheduleEntity> getScheduleEntities() {
        return scheduleEntities;
    }

    public void setScheduleEntities(Set<TrainScheduleEntity> scheduleEntities) {
        this.scheduleEntities = scheduleEntities;
    }
}
