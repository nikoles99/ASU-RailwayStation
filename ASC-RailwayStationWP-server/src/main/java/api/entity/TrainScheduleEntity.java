package api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@Entity
@Table(name = "train_schedules")
public class TrainScheduleEntity extends AbstractEntity implements Comparable<TrainScheduleEntity> {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "train_id")
    TrainEntity trainEntity;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "station_id")
    StationEntity stationEntity;

    @Column(name = "arrival_date")
    Date arrivalDate;

    @Column(name = "departure_date")
    Date departureDate;

    @ManyToOne(cascade = {CascadeType.ALL})
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrainScheduleEntity that = (TrainScheduleEntity) o;

        if (trainEntity != null ? !trainEntity.equals(that.trainEntity) : that.trainEntity != null) return false;
        if (stationEntity != null ? !stationEntity.equals(that.stationEntity) : that.stationEntity != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null) return false;
        return orderEntity != null ? orderEntity.equals(that.orderEntity) : that.orderEntity == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (trainEntity != null ? trainEntity.hashCode() : 0);
        result = 31 * result + (stationEntity != null ? stationEntity.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (orderEntity != null ? orderEntity.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(TrainScheduleEntity o) {
        return o.getArrivalDate().compareTo(this.getArrivalDate());
    }
}
