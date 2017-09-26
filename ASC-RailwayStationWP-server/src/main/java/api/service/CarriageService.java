package api.service;

import api.model.CarriageBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nikita on 14.03.17.
 */
@Transactional
public interface CarriageService {

    CarriageBean getById(Integer id);
}
