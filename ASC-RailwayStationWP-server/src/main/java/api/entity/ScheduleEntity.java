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

    @Override
    public int compareTo(ScheduleEntity o) {
        return o.getArrivalDate().compareTo(this.getArrivalDate());
    }
}
