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
    List<ScheduleEntity> schedules;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<PassengerEntity> passengers;

    @Column(name = "price")
    Double price;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    CabinetEntity cabinetEntity;

    public OrderEntity() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        for (ScheduleEntity schedule : schedules) {
            schedule.setOrderEntity(this);
        }
        this.schedules = schedules;
    }

    public List<PassengerEntity> getPassangers() {
        return passengers;
    }

    public void setPassangers(List<PassengerEntity> passengers) {
        for (PassengerEntity passenger : passengers) {
            passenger.setOrderEntity(this);
        }
        this.passengers = passengers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CabinetEntity getCabinetEntity() {
        return cabinetEntity;
    }

    public void setCabinetEntity(CabinetEntity cabinetEntity) {
        this.cabinetEntity = cabinetEntity;
    }
}
