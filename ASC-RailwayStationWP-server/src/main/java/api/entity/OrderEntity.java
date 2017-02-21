package api.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@Entity
@Table(name = "orders")
public class OrderEntity extends AbstractEntity {

    @Column(name = "time")
    Date time;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<TrainScheduleEntity> schedules;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<PassengerEntity> passengers;

    @Column(name = "price")
    Double price;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    PersonalCabinetEntity personalCabinetEntity;

    public OrderEntity() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<TrainScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TrainScheduleEntity> schedules) {
        this.schedules = schedules;
    }

    public List<PassengerEntity> getPassangers() {
        return passengers;
    }

    public void setPassangers(List<PassengerEntity> passengers) {
        this.passengers = passengers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PersonalCabinetEntity getPersonalCabinetEntity() {
        return personalCabinetEntity;
    }

    public void setPersonalCabinetEntity(PersonalCabinetEntity personalCabinetEntity) {
        this.personalCabinetEntity = personalCabinetEntity;
    }
}
