package api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public class TrainBean extends AbstractBean {

    private List<CarriageBean> carriages = new ArrayList<CarriageBean>();

    private List<TrainScheduleBean> schedules = new ArrayList<TrainScheduleBean>();

    public TrainBean() {
    }

    public List<CarriageBean> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<CarriageBean> carriages) {
        this.carriages = carriages;
    }

    public List<TrainScheduleBean> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TrainScheduleBean> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrainBean trainBean = (TrainBean) o;

        if (carriages != null ? !carriages.equals(trainBean.carriages) : trainBean.carriages != null) return false;
        return schedules != null ? schedules.equals(trainBean.schedules) : trainBean.schedules == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carriages != null ? carriages.hashCode() : 0);
        result = 31 * result + (schedules != null ? schedules.hashCode() : 0);
        return result;
    }
}
