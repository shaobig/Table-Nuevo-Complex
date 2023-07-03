package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
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
    void calculateResolveMarks() {
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));

        basicAlbumMarkCalculator.calculate(sourceMarks);

        List<SongMetadata> expected = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));
        Mockito.verify(basicMarkResolver).resolveMarks(expected);
    }

    @Test
    void calculateAverageCalculator() {
        List<Integer> sourceMarkResolverResponse = List.of(1);
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()));
        Mockito.when(basicMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceMarkResolverResponse);

        basicAlbumMarkCalculator.calculate(sourceMarks);

        List<Integer> expectedMarks = List.of(1);
        Mockito.verify(averageCalculator).calculate(expectedMarks);
    }

    //TODO: Review the list content
    static Stream<Arguments> calculateInputData() {
        return Stream.of(
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song())), new BigDecimal("1"), new BigDecimal("1")),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.DEFAULT, 1, new Song())), new BigDecimal("1"), new BigDecimal("1")),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song())), new BigDecimal("1.000"), new BigDecimal("1.000")),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.DEFAULT, 2, new Song()), new SongMetadata(1L, SongType.DEFAULT, 3, new Song())), new BigDecimal("1.500"), new BigDecimal("1.500")),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), new BigDecimal("1.500"), new BigDecimal("1.500")),
                Arguments.of(List.of(new SongMetadata(1L, SongType.COVER, 1, new Song()), new SongMetadata(1L, SongType.COVER, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), new BigDecimal("1.000"), new BigDecimal("1.000"))
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
