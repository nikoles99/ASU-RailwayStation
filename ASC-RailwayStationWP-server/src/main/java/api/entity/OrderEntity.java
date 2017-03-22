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
    private Date time;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
    private List<ScheduleEntity> schedules;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
    private List<PassengerEntity> passengers;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private CabinetEntity cabinet;

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
            schedule.setOrder(this);
        }
        this.schedules = schedules;
    }

    public List<PassengerEntity> getPassangers() {
        return passengers;
    }

    public void setPassangers(List<PassengerEntity> passengers) {
        for (PassengerEntity passenger : passengers) {
            passenger.setOrder(this);
        }
        this.passengers = passengers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }
}
