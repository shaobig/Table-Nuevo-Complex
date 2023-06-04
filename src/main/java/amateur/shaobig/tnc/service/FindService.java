package amateur.shaobig.tnc.service;

import java.io.Serializable;

public interface FindService<T extends Serializable> {

    boolean isFound(T entity);

}
