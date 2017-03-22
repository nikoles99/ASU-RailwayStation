package api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@Entity
@Table(name = "train_schedules")
public class ScheduleEntity extends AbstractEntity implements Comparable<ScheduleEntity> {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "train_id")
    private TrainEntity train;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "station_id")
    private StationEntity station;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public ScheduleEntity() {
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public StationEntity getStation() {
        return station;
    }

    public void setStation(StationEntity station) {
        this.station = station;
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (train != null ? !train.equals(that.train) : that.train != null) return false;
        if (station != null ? !station.equals(that.station) : that.station != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (train != null ? train.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ScheduleEntity o) {
        return o.getArrivalDate().compareTo(this.getArrivalDate());
    }
}
