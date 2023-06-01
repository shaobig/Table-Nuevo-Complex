package amateur.shaobig.tnc.transformer;

import java.io.Serializable;

public interface Transformer<E extends Serializable, R extends Record> {

    R transform(E entity);

}
