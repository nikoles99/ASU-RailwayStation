package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

   // List<TrainScheduleEntity> schedules;

    //List<PassengerEntity> passangers;

    @Column(name = "price")
    Double price;

}
