package amateur.shaobig.tnc.calculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalSumCalculatorTest {

    private BigDecimalSumCalculator bigDecimalSumCalculator;

    @BeforeEach
    void init() {
        this.bigDecimalSumCalculator = new BigDecimalSumCalculator();
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(BigDecimal.valueOf(1)), BigDecimal.valueOf(1)),
                Arguments.of(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)), BigDecimal.valueOf(6)),
                Arguments.of(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5)), BigDecimal.valueOf(15))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<BigDecimal> sourceMarks, BigDecimal expected) {
        BigDecimal actual = bigDecimalSumCalculator.calculate(sourceMarks);

        assertEquals(expected, actual);
    }

}
