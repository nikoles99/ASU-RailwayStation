package api.service.impl;

import api.convertors.CarriageConverter;
import api.dao.CarriageDao;
import api.entity.CarriageEntity;
import api.model.CarriageBean;
import api.service.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Service
@Transactional
public class CarriageServiceImpl implements CarriageService {

    @Autowired
    private CarriageDao carriageDao;

    @Autowired
    private CarriageConverter carriageConverter;

    @Override
    public CarriageBean getById(Integer id) {
        CarriageEntity carriageEntity = carriageDao.get(id);
        return carriageConverter.toBean(carriageEntity);
    }
}
