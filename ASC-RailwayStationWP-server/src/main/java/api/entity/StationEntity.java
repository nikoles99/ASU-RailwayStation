package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "stations")
public class StationEntity extends AbstractEntity {

    @Column(name = "name")
    String name;

    List<TrainScheduleEntity> schedules;
}
