package api.model;

import java.util.Set;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public class TrainBean extends AbstractBean {

    private Integer number;

    private Set<CarriageBean> carriages;

    private Set<TrainScheduleBean> schedules;

    public TrainBean() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<CarriageBean> getCarriages() {
        return carriages;
    }

    public void setCarriages(Set<CarriageBean> carriages) {
        this.carriages = carriages;
    }

    public Set<TrainScheduleBean> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<TrainScheduleBean> schedules) {
        this.schedules = schedules;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        TrainBean trainBean = (TrainBean) o;

        if (number != null ? !number.equals(trainBean.number) : trainBean.number != null)
            return false;
        if (carriages != null ? !carriages.equals(trainBean.carriages) : trainBean.carriages != null)
            return false;
        return carriages != null ? carriages.equals(trainBean.carriages) : trainBean.carriages == null;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (carriages != null ? carriages.hashCode() : 0);
        result = 31 * result + (carriages != null ? carriages.hashCode() : 0);
        return result;
    }
}
