package api.dao;

import api.entity.CabinetEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface CabinetDao {

    void add(CabinetEntity cabinet);

    CabinetEntity get(Integer id);

    void update(CabinetEntity cabinet);

    void remove(CabinetEntity cabinet);
}
