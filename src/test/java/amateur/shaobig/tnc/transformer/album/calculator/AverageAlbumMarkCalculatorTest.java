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

class AverageAlbumMarkCalculatorTest {

    private AverageCalculator<BigDecimal> averageCalculator;

    private AverageAlbumMarkCalculator averageAlbumMarkCalculator;

    @BeforeEach
    void init() {
        this.averageCalculator = Mockito.mock(AverageCalculator.class);

        this.averageAlbumMarkCalculator = new AverageAlbumMarkCalculator(averageCalculator);
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(BigDecimal.valueOf(1)), new BigDecimal("1.000"), new BigDecimal("1.000")),
                Arguments.of(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2)), new BigDecimal("1.500"), new BigDecimal("1.500")),
                Arguments.of(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(3)), new BigDecimal("1.667"), new BigDecimal("1.667")),
                Arguments.of(List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(4)), new BigDecimal("2.500"), new BigDecimal("2.500"))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<BigDecimal> sourceMarks, BigDecimal sourceCalculatorAnswer, BigDecimal expected) {
        Mockito.when(averageCalculator.calculate(Mockito.anyList())).thenReturn(sourceCalculatorAnswer);

        BigDecimal actual = averageAlbumMarkCalculator.calculate(sourceMarks);

        assertEquals(expected, actual);
    }

}
