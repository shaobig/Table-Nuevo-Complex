package amateur.shaobig.table_nuevo_complex.service;

import java.io.Serializable;

public interface MergeService<T extends Serializable> {

    T merge(T entity);

}
