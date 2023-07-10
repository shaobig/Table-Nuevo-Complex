package amateur.shaobig.tnc.calculator.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BigDecimalSumCalculator implements Calculator<BigDecimal> {

    @Override
    public BigDecimal calculate(List<BigDecimal> marks) {
        return marks.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
