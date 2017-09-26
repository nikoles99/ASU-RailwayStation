package api.dao;

import api.entity.StationEntity;
import api.exception.StationException;
import api.model.SimpleResponseBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface StationDao {

    void add(StationEntity station) throws StationException;

    List<StationEntity>  getByName(String name);

    void update(StationEntity station);

    List<StationEntity> getAllStations();

    void delete(StationEntity station);

    StationEntity getById(Integer id);
}
