package amateur.shaobig.tnc.calculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativeSumFinalAlbumMarkCalculatorTest {

    private RelativeSumFinalAlbumMarkCalculator relativeSumFinalAlbumMarkCalculator;

    @BeforeEach
    void init() {
        this.relativeSumFinalAlbumMarkCalculator = new RelativeSumFinalAlbumMarkCalculator();
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(new BigDecimal("1.000"), new BigDecimal("1"), new BigDecimal("1.001")),
                Arguments.of(new BigDecimal("1.334"), new BigDecimal("3"), new BigDecimal("1.338")),
                Arguments.of(new BigDecimal("1.667"), new BigDecimal("5"), new BigDecimal("1.675")),
                Arguments.of(new BigDecimal("2.000"), new BigDecimal("2"), new BigDecimal("2.004")),
                Arguments.of(new BigDecimal("2.000"), new BigDecimal("20"), new BigDecimal("2.040"))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(BigDecimal sourceTotalMark, BigDecimal sourceSum, BigDecimal expected) {
        BigDecimal actual = relativeSumFinalAlbumMarkCalculator.calculate(sourceTotalMark, sourceSum);

        assertEquals(expected, actual);
    }

}
