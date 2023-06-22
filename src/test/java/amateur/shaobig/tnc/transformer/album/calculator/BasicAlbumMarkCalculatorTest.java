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

class BasicAlbumMarkCalculatorTest {

    private BasicMarkResolver basicMarkResolver;
    private AverageCalculator<Integer> averageCalculator;

    private BasicAlbumMarkCalculator basicAlbumMarkCalculator;

    @BeforeEach
    void init() {
        this.basicMarkResolver = Mockito.mock(BasicMarkResolver.class);
        this.averageCalculator = Mockito.mock(AverageCalculator.class);
        
        this.basicAlbumMarkCalculator = new BasicAlbumMarkCalculator(basicMarkResolver, averageCalculator);
    }

    @Test
    void calculateResolveMarksInputArgument() {
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 1970))));

        basicAlbumMarkCalculator.calculate(sourceMarks);

        List<SongMetadata> expected = List.of(new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 1970))));
        Mockito.verify(basicMarkResolver).resolveMarks(expected);
    }

    @Test
    void calculateAverageCalculatorInputArgument() {
        List<Integer> sourceMarkResolverResponse = List.of(1);
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(SongType.DEFAULT, 1));
        Mockito.when(basicMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceMarkResolverResponse);

        basicAlbumMarkCalculator.calculate(sourceMarks);

        List<Integer> expectedMarks = List.of(1);
        Mockito.verify(averageCalculator).calculate(expectedMarks);
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
    void calculate(List<SongMetadata> sourceSongMetadataList, BigDecimal sourceAverageCalculatorAnswer, BigDecimal expected) {
        Mockito.when(averageCalculator.calculate(Mockito.anyList())).thenReturn(sourceAverageCalculatorAnswer);

        BigDecimal actual = basicAlbumMarkCalculator.calculate(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
