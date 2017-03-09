package api.model;

import api.entity.PlaceEntity;
import api.entity.TrainEntity;

import java.util.Set;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public class CarriageBean extends AbstractBean {

    private CarriageType carriageType;

    private Integer number;

    private Integer trainId;

    private Set<PlaceEntity> places;

    public CarriageBean(){

    }

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

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Set<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceEntity> places) {
        this.places = places;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        CarriageBean that = (CarriageBean) o;

        if (carriageType != that.carriageType)
            return false;
        if (number != null ? !number.equals(that.number) : that.number != null)
            return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null)
            return false;
        return places != null ? places.equals(that.places) : that.places == null;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carriageType != null ? carriageType.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (places != null ? places.hashCode() : 0);
        return result;
    }
}
