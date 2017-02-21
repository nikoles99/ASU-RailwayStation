package api.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "trains")
public class TrainEntity extends AbstractEntity {

    @Column(name = "number")
    Integer number;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    Set<CarriageEntity> carriages;

    @OneToMany(mappedBy = "bankField", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<TrainScheduleEntity> scheduleEntities;

}
