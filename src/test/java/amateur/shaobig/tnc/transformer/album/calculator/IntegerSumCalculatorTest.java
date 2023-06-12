package amateur.shaobig.tnc.transformer.album.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerSumCalculatorTest {

    private IntegerSumCalculator integerSumCalculator;

    @BeforeEach
    void init() {
        this.integerSumCalculator = new IntegerSumCalculator();
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(), BigDecimal.ZERO),
                Arguments.of(List.of(1), BigDecimal.ONE),
                Arguments.of(List.of(1, 2, 3), BigDecimal.valueOf(6)),
                Arguments.of(List.of(1, 2, 3, 4, 5), BigDecimal.valueOf(15))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<Integer> sourceMarks, BigDecimal expected) {
        BigDecimal actual = integerSumCalculator.calculate(sourceMarks);

        assertEquals(expected, actual);
    }

}
