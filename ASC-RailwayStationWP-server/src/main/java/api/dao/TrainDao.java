package api.dao;

import api.entity.TrainEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface TrainDao {

    void add(TrainEntity train);

    TrainEntity get(Integer id);

    void update(TrainEntity train);

    void remove(TrainEntity train);

    List<TrainEntity> getByStations(String arrivalStation, String departureStation);

    List<TrainEntity> getByParams(String arrivalStation, String departureDate, Date arrivalDate, Date departureDate1);

}

