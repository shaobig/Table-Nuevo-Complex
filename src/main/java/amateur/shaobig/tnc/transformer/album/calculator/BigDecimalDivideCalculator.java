package amateur.shaobig.tnc.transformer.album.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class BigDecimalDivideCalculator extends AverageCalculator<BigDecimal> {

    public BigDecimalDivideCalculator(BigDecimalSumCalculator sumCalculator, ListSizeCalculator<BigDecimal> sizeDivideCalculator) {
        super(sumCalculator, sizeDivideCalculator);
    }

}
