package amateur.shaobig.tnc.transformer.album.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListSizeCalculatorTest {

    private ListSizeCalculator<Object> listSizeCalculator;

    @BeforeEach
    void init() {
        this.listSizeCalculator = new ListSizeCalculator<>();
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(new Object()), BigDecimal.valueOf(1)),
                Arguments.of(List.of(new Object(), new Object(), new Object()), BigDecimal.valueOf(3))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<Object> sourceMarks, BigDecimal expected) {
        BigDecimal actual = listSizeCalculator.calculate(sourceMarks);

        assertEquals(expected, actual);
    }

}
