package amateur.shaobig.tnc.calculator.calculator;

import java.math.BigDecimal;
import java.util.List;

public interface Calculator<T> {

    BigDecimal calculate(List<T> inputData);

}
