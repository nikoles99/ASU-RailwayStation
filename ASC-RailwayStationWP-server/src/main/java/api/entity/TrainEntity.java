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

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    Set<CarriageEntity> carriages;

    @OneToMany(mappedBy = "trainEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrainEntity that = (TrainEntity) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (carriages != null ? !carriages.equals(that.carriages) : that.carriages != null) return false;
        return scheduleEntities != null ? scheduleEntities.equals(that.scheduleEntities) : that.scheduleEntities == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (carriages != null ? carriages.hashCode() : 0);
        result = 31 * result + (scheduleEntities != null ? scheduleEntities.hashCode() : 0);
        return result;
    }
}
