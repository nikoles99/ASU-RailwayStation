package api.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nolesuk on 07-Mar-17.
 */
public class TrainBean extends AbstractBean {

    @NotNull
    private String name;

    @NotNull
    private List<CarriageBean> carriages = new ArrayList<CarriageBean>();

    @NotNull
    private List<ScheduleBean> schedules = new ArrayList<ScheduleBean>();

    public TrainBean() {
    }

    public List<CarriageBean> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<CarriageBean> carriages) {
        this.carriages = carriages;
    }

    public List<ScheduleBean> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleBean> schedules) {
        Collections.sort(schedules);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
