package api.entity;

import javax.persistence.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "places")
public class PlaceEntity extends AbstractEntity {

    @Column(name = "number")
    Integer number;

    @ManyToOne
    @JoinColumn(name = "carriage_id")
    CarriageEntity  carriageEntity;

    @Column(name = "train_id")
    TrainEntity  trainEntity;
}
