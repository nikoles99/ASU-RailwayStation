package api.entity;

import api.model.CarriageType;

import javax.persistence.*;
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
    Set<PlaceEntity> places;

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

    public Set<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceEntity> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CarriageEntity that = (CarriageEntity) o;

        if (carriageType != that.carriageType) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (trainEntity != null ? !trainEntity.equals(that.trainEntity) : that.trainEntity != null) return false;
        return places != null ? places.equals(that.places) : that.places == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carriageType != null ? carriageType.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (trainEntity != null ? trainEntity.hashCode() : 0);
        result = 31 * result + (places != null ? places.hashCode() : 0);
        return result;
    }
}
