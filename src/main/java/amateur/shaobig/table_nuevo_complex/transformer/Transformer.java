package amateur.shaobig.table_nuevo_complex.transformer;

import java.io.Serializable;

public interface Transformer<E extends Serializable, R extends Record> {

    R transform(E entity);

}
