package amateur.shaobig.tnc.transformer;

public interface Transformer<E, R extends Record> {

    R transform(E entity);

}
