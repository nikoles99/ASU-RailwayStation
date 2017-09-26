package api.dao;

import api.entity.ScheduleEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface ScheduleDao {

    void add(ScheduleEntity schedule);

    ScheduleEntity get(Integer id);

    void update(ScheduleEntity schedule);

    void remove(ScheduleEntity schedule);

    Boolean isValidate(ScheduleEntity schedule);

    List<ScheduleEntity> getByParams(Date departureDate, Date arrivalDate, Integer stationId);
}
