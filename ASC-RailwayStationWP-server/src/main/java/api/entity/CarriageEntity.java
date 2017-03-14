package api.entity;

import api.model.CarriageType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "carriages")
public class CarriageEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    CarriageType carriageType;

    @Column(name = "number")
    Integer number;

    @ManyToOne
    @JoinColumn(name = "train_id")
    TrainEntity trainEntity;

    @OneToMany(mappedBy = "carriageEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    List<PlaceEntity> places;

    public CarriageType getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(CarriageType carriageType) {
        this.carriageType = carriageType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CarriageEntity() {
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void setTrainEntity(TrainEntity trainEntity) {
        this.trainEntity = trainEntity;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        for (PlaceEntity place : places) {
            place.setCarriageEntity(this);
        }
        this.places = places;
    }

}
