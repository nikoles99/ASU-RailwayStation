package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "stations")
public class StationEntity extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "station", cascade = {CascadeType.ALL})
    private List<ScheduleEntity> schedules;

    public StationEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        for (ScheduleEntity schedule : schedules) {
            schedule.setStation(this);
        }
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "name='" + name + '\'' +
                ", schedules=" + schedules +
                '}';
    }
}
