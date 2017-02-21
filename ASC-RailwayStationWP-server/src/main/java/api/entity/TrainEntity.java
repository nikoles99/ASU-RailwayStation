package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "trains")
public class TrainEntity extends AbstractEntity {

    @Column(name = "number")
    Integer number;

    List<CarriageEntity> carriages;

    List<TrainScheduleEntity> scheduleEntities;

}
