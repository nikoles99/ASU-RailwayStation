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

    @OneToOne
    @JoinColumn(name = "train_id")
    TrainEntity  trainEntity;

    public PlaceEntity() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CarriageEntity getCarriageEntity() {
        return carriageEntity;
    }

    public void setCarriageEntity(CarriageEntity carriageEntity) {
        this.carriageEntity = carriageEntity;
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }
}
