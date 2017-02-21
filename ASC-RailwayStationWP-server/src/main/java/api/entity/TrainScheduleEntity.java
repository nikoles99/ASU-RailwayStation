package api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@Entity
@Table(name = "train_schedules")
public class TrainScheduleEntity extends AbstractEntity {

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "train_id")
    TrainEntity trainEntity;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "station_id")
    StationEntity stationEntity;

    @Column(name = "arrival_date")
    Date arrivaleDate;

    @Column(name = "departure_date")
    Date departureDate;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;

    public TrainScheduleEntity() {
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }

    public StationEntity getStationEntity() {
        return stationEntity;
    }

    public void setStationEntity(StationEntity stationEntity) {
        this.stationEntity = stationEntity;
    }

    public Date getArrivaleDate() {
        return arrivaleDate;
    }

    public void setArrivaleDate(Date arrivaleDate) {
        this.arrivaleDate = arrivaleDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
