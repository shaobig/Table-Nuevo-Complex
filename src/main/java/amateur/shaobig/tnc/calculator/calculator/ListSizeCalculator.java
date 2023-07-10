package amateur.shaobig.tnc.calculator.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ListSizeCalculator<T> implements Calculator<T> {

    @Override
    public BigDecimal calculate(List<T> inputData) {
        return BigDecimal.valueOf(inputData.size());
    }

}
