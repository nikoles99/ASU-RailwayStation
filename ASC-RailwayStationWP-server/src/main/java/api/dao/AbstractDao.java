package api.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
public class AbstractDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    protected void persist(T entity) {
        entityManager.persist(entity);
        LOGGER.info("Persist was successfully " + entity);
    }

    protected T getById(Class<T> type, Integer id) {
        if (id != null) {
            return entityManager.find(type, id);
        }
        return null;
    }

    protected List<T> getAll(Class<T> type) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        List<T> list = entityManager.createQuery(criteriaQuery).getResultList();
        return list;
    }

    protected void remove(T entity) {
        entityManager.remove(entity);
        LOGGER.info("Remove was successfully " + entity);
    }

    protected T merge(T entity) {
        return entityManager.merge(entity);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Logger getLogger() {
        return LOGGER;
    }

}
