package amateur.shaobig.tnc.calculator.calculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AverageCalculator<T> implements Calculator<T> {

    private static final int DIVIDING_SCALE = 3;

    private final Calculator<T> sumCalculator;
    private final ListSizeCalculator<T> listSizeCalculator;

    @Override
    public BigDecimal calculate(List<T> marks) {
        return getSumCalculator().calculate(marks)
                .divide(getListSizeCalculator().calculate(marks), DIVIDING_SCALE, RoundingMode.HALF_UP);
    }

}
