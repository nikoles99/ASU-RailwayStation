package api.convertors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
public abstract class AbstractConvertor<B, E> {

    abstract public B convertToBean(E entity);

    abstract public E convertToEntity(B bean);

    public List<E> convertToEntityCollection(List<B> beans) {
        List<E> entities = new ArrayList<E>();
        for (B bean : beans) {
            E entity = convertToEntity(bean);
            entities.add(entity);
        }
        return entities;
    }

    public List<B> convertToBeanCollection(List<E> entities) {
        List<B> beans = new ArrayList<B>();
        for (E entity : entities) {
            B trainBean = convertToBean(entity);
            beans.add(trainBean);
        }
        return beans;
    }
}
