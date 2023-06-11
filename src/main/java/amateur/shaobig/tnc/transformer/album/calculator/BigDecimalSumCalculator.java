package amateur.shaobig.tnc.transformer.album.calculator;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalSumCalculator implements Calculator<BigDecimal> {

    @Override
    public BigDecimal calculate(List<BigDecimal> marks) {
        return marks.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
