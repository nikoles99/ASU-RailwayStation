package api.entity;

import api.model.CarriageType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "carriages")
public class CarriageEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private CarriageType type;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private TrainEntity train;

    @OneToMany(mappedBy = "carriage", cascade = {CascadeType.ALL})
    private List<PlaceEntity> places;

    public CarriageType getType() {
        return type;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CarriageEntity() {
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        for (PlaceEntity place : places) {
            place.setCarriage(this);
        }
        this.places = places;
    }

}
