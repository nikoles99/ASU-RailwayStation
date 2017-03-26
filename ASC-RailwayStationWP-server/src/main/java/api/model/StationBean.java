package api.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nolesuk on 01-Mar-17.
 */
public class StationBean extends AbstractBean {

    @NotNull
    private String name;

    private List<ScheduleBean> schedules = new ArrayList<ScheduleBean>();

    public StationBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleBean> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleBean> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        api.model.StationBean that = (api.model.StationBean) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return schedules != null ? schedules.equals(that.schedules) : that.schedules == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (schedules != null ? schedules.hashCode() : 0);
        return result;
    }
}
