package amateur.shaobig.tnc.transformer.album.calculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
abstract class DivideCalculator<T> implements Calculator<T> {

    private static final int DIVIDING_SCALE = 3;

    private final Calculator<T> sumCalculator;
    private final ListSizeCalculator<T> sizeDivideCalculator;

    @Override
    public BigDecimal calculate(List<T> marks) {
        return getSumCalculator().calculate(marks)
                .divide(getSizeDivideCalculator().calculate(marks), DIVIDING_SCALE, RoundingMode.UP);
    }

}
