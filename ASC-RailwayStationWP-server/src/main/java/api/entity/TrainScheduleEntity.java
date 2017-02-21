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
}
