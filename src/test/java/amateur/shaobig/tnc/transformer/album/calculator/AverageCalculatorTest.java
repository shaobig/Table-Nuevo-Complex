package amateur.shaobig.tnc.transformer.album.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageCalculatorTest {

    private Calculator<Integer> sumCalculator;
    private ListSizeCalculator<Integer> listSizeCalculator;

    private AverageCalculator<Integer> averageCalculator;

    @BeforeEach
    void init() {
        this.sumCalculator = Mockito.mock(Calculator.class);
        this.listSizeCalculator = Mockito.mock(ListSizeCalculator.class);

        this.averageCalculator = new AverageCalculator<>(sumCalculator, listSizeCalculator);
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(1), new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1.000")),
                Arguments.of(List.of(1, 1), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("1.000")),
                Arguments.of(List.of(1, 1, 3), new BigDecimal("5"), new BigDecimal("3"), new BigDecimal("1.667")),
                Arguments.of(List.of(1, 2, 3), new BigDecimal("6"), new BigDecimal("3"), new BigDecimal("2.000")),
                Arguments.of(List.of(1, 2, 3, 4), new BigDecimal("10"), new BigDecimal("4"), new BigDecimal("2.500")),
                Arguments.of(List.of(1, 2, 3, 4, 5), new BigDecimal("15"), new BigDecimal("5"), new BigDecimal("3.000"))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<Integer> sourceMarks, BigDecimal sourceSumCalculatorAnswer, BigDecimal sourceListSizeCalculatorAnswer, BigDecimal expected) {
        Mockito.when(sumCalculator.calculate(Mockito.anyList())).thenReturn(sourceSumCalculatorAnswer);
        Mockito.when(listSizeCalculator.calculate(Mockito.anyList())).thenReturn(sourceListSizeCalculatorAnswer);

        BigDecimal actual = averageCalculator.calculate(sourceMarks);

        assertEquals(expected, actual);
    }

}
