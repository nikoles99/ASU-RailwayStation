package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.OrderDao;
import api.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class OrderDaoImpl extends AbstractDao<OrderEntity> implements OrderDao {

    @Override
    public void add(OrderEntity order) {

    }

    @Override
    public OrderEntity getOrder(Integer id) {
        return getById(OrderEntity.class, id);
    }

    @Override
    public void update(OrderEntity order) {

    }

    @Override
    public void remove(OrderEntity order) {

    }
}
