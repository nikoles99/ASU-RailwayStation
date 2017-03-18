package api.dao.cabinet;

import api.dao.AbstractDao;
import api.entity.CabinetEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class CabinetDaoImpl extends AbstractDao<CabinetEntity> implements CabinetDao {

    @Override
    public void addCabinet(CabinetEntity cabinetEntity) {

    }

    @Override
    public CabinetEntity getCabinet(Integer id) {
        return null;
    }

    @Override
    public void updateCabinet(CabinetEntity cabinetEntity) {

    }

    @Override
    public void removeCabinet(CabinetEntity cabinetEntity) {

    }
}