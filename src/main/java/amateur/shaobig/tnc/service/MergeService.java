package amateur.shaobig.tnc.service;

import java.io.Serializable;

public interface MergeService<T extends Serializable> {

    T merge(T entity);

}
