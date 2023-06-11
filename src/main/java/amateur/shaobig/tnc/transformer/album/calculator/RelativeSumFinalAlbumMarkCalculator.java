package amateur.shaobig.tnc.transformer.album.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class RelativeSumFinalAlbumMarkCalculator implements FinalAlbumMarkCalculator {

    private static final BigDecimal SUM_DENOMINATOR = BigDecimal.valueOf(1000);
    private static final int SUM_DIVIDING_SCALE = 3;
    private static final int BASIC_DIVIDING_SCALE = 3;

    @Override
    public BigDecimal calculate(BigDecimal totalMark, BigDecimal sum) {
        return totalMark
                .multiply(BigDecimal.ONE.add(sum.divide(SUM_DENOMINATOR, SUM_DIVIDING_SCALE, RoundingMode.UP)))
                .divide(BigDecimal.ONE, BASIC_DIVIDING_SCALE, RoundingMode.FLOOR);
    }

}
