package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.mark.BasicMarkResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumAlbumMarkCalculatorTest {

    private BasicMarkResolver basicMarkResolver;
    private IntegerSumCalculator integerSumCalculator;

    private SumAlbumMarkCalculator sumAlbumMarkCalculator;

    @BeforeEach
    void init() {
        this.basicMarkResolver = Mockito.mock(BasicMarkResolver.class);
        this.integerSumCalculator = Mockito.mock(IntegerSumCalculator.class);

        this.sumAlbumMarkCalculator = new SumAlbumMarkCalculator(basicMarkResolver, integerSumCalculator);
    }

    @Test
    void calculateResolveMarksInputArgument() {
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 1970))));

        sumAlbumMarkCalculator.calculate(sourceMarks);

        List<SongMetadata> expected = List.of(new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 1970))));
        Mockito.verify(basicMarkResolver).resolveMarks(expected);
    }

    @Test
    void calculateIntegerSumCalculatorInputArgument() {
        List<Integer> sourceMarkResolverResponse = List.of(1);
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(SongType.DEFAULT, 1));
        Mockito.when(basicMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceMarkResolverResponse);

        sumAlbumMarkCalculator.calculate(sourceMarks);

        List<Integer> expectedMarks = List.of(1);
        Mockito.verify(integerSumCalculator).calculate(expectedMarks);
    }

    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1)), new BigDecimal("1.000"), new BigDecimal("1.000")),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.COVER, 2)), new BigDecimal("1.000"), new BigDecimal("1.000")),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.INSTRUMENTAL, 2)), new BigDecimal("1.000"), new BigDecimal("1.000")),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.DEFAULT, 2), new SongMetadata(SongType.DEFAULT, 3)), new BigDecimal("2.000"), new BigDecimal("2.000")),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.INSTRUMENTAL, 2)), new BigDecimal("0.000"), new BigDecimal("0.000")),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.COVER, 2)), new BigDecimal("1.000"), new BigDecimal("1.000"))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "calculateInputData")
    void calculate(List<SongMetadata> sourceSongMetadataList, BigDecimal sourceCalculatorAnswer, BigDecimal expected) {
        Mockito.when(integerSumCalculator.calculate(Mockito.anyList())).thenReturn(sourceCalculatorAnswer);

        BigDecimal actual = sumAlbumMarkCalculator.calculate(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
