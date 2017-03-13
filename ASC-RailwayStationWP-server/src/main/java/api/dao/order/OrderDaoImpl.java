package api.dao.order;

import api.dao.user.UserDaoImpl;
import api.entity.OrderEntity;
import api.entity.PlaceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nolesuk on 13-Mar-17.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrder(OrderEntity orderEntity) {

    }

    @Override
    public OrderEntity getOrder(Integer id) {
        if (id != null) {
            return entityManager.find(OrderEntity.class, id);
        }
        return null;
    }

    @Override
    public void updateOrder(OrderEntity orderEntity) {

    }

    @Override
    public void removeOrder(OrderEntity orderEntity) {

    }
}
