package amateur.shaobig.tnc.transformer.album.calculator;

import java.math.BigDecimal;
import java.util.List;

public interface Calculator<T> {

    BigDecimal calculate(List<T> inputData);

}
