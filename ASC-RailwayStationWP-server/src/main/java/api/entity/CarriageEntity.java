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
    CarriageType carriageType;

    @Column(name = "number")
    Integer number;

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

    public List<PlaceEntity> getPrices() {
        return places;
    }

    public void setPrices(List<PlaceEntity> prices) {
        this.places = prices;
    }
}
