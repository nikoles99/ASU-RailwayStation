package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@Entity
@Table(name = "train_schedules")
public class TrainScheduleEntity extends AbstractEntity {

    @Column(name = "train_id")
    TrainEntity trainEntity;

    @Column(name = "station_id")
    StationEntity stationEntity;

    @Column(name = "arrival_date")
    Date arrivaleDate;

    @Column(name = "departure_date")
    Date departureDate;
}
