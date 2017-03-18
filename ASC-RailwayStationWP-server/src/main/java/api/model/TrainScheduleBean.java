package api.model;

import java.util.Date;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public class TrainScheduleBean extends AbstractBean implements Comparable<TrainScheduleBean> {

    private Integer trainId;

    private Integer stationId;

    private Date departureDate;

    private Date arrivalDate;

    public Integer getOrderId() {
        return orderId;
    }

    private Integer orderId;

    public TrainScheduleBean() {
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrainScheduleBean that = (TrainScheduleBean) o;

        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (stationId != null ? !stationId.equals(that.stationId) : that.stationId != null) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null) return false;
        return arrivalDate != null ? arrivalDate.equals(that.arrivalDate) : that.arrivalDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (stationId != null ? stationId.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        return result;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int compareTo(TrainScheduleBean o) {
        return o.getArrivalDate().compareTo(this.getArrivalDate());
    }
}
