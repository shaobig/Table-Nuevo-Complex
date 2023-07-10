package amateur.shaobig.tnc.calculator.calculator;

import amateur.shaobig.tnc.calculator.mark.AllSongMarkResolver;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.SongType;
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

class FullAlbumMarkCalculatorTest {

    private AllSongMarkResolver allSongMarkResolver;
    private AverageCalculator<Integer> averageCalculator;

    private FullAlbumMarkCalculator fullAlbumMarkCalculator;

    @BeforeEach
    void init() {
        this.allSongMarkResolver = Mockito.mock(AllSongMarkResolver.class);
        this.averageCalculator = Mockito.mock(AverageCalculator.class);

        this.fullAlbumMarkCalculator = new FullAlbumMarkCalculator(allSongMarkResolver, averageCalculator);
    }

    @Test
    void calculateResolveMarks() {
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));

        fullAlbumMarkCalculator.calculate(sourceMarks);

        List<SongMetadata> expected = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));
        Mockito.verify(allSongMarkResolver).resolveMarks(expected);
    }

    @Test
    void calculateAverageCalculator() {
        List<Integer> sourceMarkResolverResponse = List.of(1);
        List<SongMetadata> sourceMarks = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()));
        Mockito.when(allSongMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceMarkResolverResponse);

        fullAlbumMarkCalculator.calculate(sourceMarks);

        List<Integer> expectedMarks = List.of(1);
        Mockito.verify(averageCalculator).calculate(expectedMarks);
    }

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
    void calculate(List<SongMetadata> sourceSongMetadataList, BigDecimal sourceCalculatorAnswer, BigDecimal expected) {
        Mockito.when(averageCalculator.calculate(Mockito.anyList())).thenReturn(sourceCalculatorAnswer);

        BigDecimal actual = fullAlbumMarkCalculator.calculate(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
