package api.convertors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 14.03.17.
 */
public abstract class AbstractConvertor<B, E> {

  abstract public B toBean(E entity);

  abstract public E toEntity(B bean);

  public List<E> toEntityCollection(List<B> beans) {
    List<E> entities = new ArrayList<>();
    beans.forEach((bean) -> {
      E entity = toEntity(bean);
      entities.add(entity);
    });
    return entities;
  }

  public List<B> toBeanCollection(List<E> entities) {
    List<B> beans = new ArrayList<>();
    entities.forEach((entity -> {
      B trainBean = toBean(entity);
      beans.add(trainBean);
    }));
    return beans;
  }
}
