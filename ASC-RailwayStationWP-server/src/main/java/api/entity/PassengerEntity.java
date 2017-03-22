package api.entity;

import javax.persistence.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "passengers")
public class PassengerEntity extends AbstractEntity {

    @Column(name = "name")
    String name;

    @OneToOne
    @JoinColumn(name = "last_name")
    ScheduleEntity scheduleEntity;

    @Column(name = "rate")
    Double rate;

    @OneToOne
    @JoinColumn(name = "place_id")
    PlaceEntity placeEntity;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;

    public PassengerEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public void setScheduleEntity(ScheduleEntity scheduleEntity) {
        this.scheduleEntity = scheduleEntity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public PlaceEntity getPlaceEntity() {
        return placeEntity;
    }

    public void setPlaceEntity(PlaceEntity placeEntity) {
        this.placeEntity = placeEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
