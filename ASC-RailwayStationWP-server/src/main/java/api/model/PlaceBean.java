package api.model;

/**
 * Created by nolesuk on 13-Mar-17.
 */
public class PlaceBean extends AbstractBean {

    private Integer number;

    private Integer carriageId;

    private Integer trainId;

    public PlaceBean() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(Integer carriageId) {
        this.carriageId = carriageId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PlaceBean placeBean = (PlaceBean) o;

        if (number != null ? !number.equals(placeBean.number) : placeBean.number != null) return false;
        if (carriageId != null ? !carriageId.equals(placeBean.carriageId) : placeBean.carriageId != null) return false;
        return trainId != null ? trainId.equals(placeBean.trainId) : placeBean.trainId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (carriageId != null ? carriageId.hashCode() : 0);
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        return result;
    }
}
