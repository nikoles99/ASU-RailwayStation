package api.dao;

import api.entity.CabinetEntity;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface CabinetDao {

    void addCabinet(CabinetEntity cabinetEntity);

    CabinetEntity getCabinet(Integer id);

    void updateCabinet(CabinetEntity cabinetEntity);

    void removeCabinet(CabinetEntity cabinetEntity);
}
