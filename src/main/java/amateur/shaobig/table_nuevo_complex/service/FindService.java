package amateur.shaobig.table_nuevo_complex.service;

import java.io.Serializable;

public interface FindService<T extends Serializable> {

    T find(T entity);

}
