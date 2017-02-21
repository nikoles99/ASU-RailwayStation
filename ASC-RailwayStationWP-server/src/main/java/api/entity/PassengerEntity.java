package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "passengers")
public class PassengerEntity extends AbstractEntity {

    @Column(name = "name")
    String name;

    @Column(name = "last_name")
    TrainScheduleEntity trainScheduleEntity;

    @Column(name = "rate")
    Double rate;

    @Column(name = "place_id")
    PlaceEntity placeEntity;

}
