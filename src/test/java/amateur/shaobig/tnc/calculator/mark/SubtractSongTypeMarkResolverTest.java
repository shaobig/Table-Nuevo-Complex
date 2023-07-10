package amateur.shaobig.tnc.calculator.mark;

import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractSongTypeMarkResolverTest {

    private SongTypeMarkResolver songTypeMarkResolver;
    private int subtractFactor;

    private SubtractSongTypeMarkResolver subtractSongTypeMarkResolver;

    @BeforeEach
    void init() {
        this.songTypeMarkResolver = Mockito.mock(SongTypeMarkResolver.class);
        this.subtractFactor = 1;

        this.subtractSongTypeMarkResolver = new SubtractSongTypeMarkResolver(songTypeMarkResolver, subtractFactor);
    }

    static Stream<Arguments> resolveMarksInputData() {
        return Stream.of(
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song())), List.of(1), List.of(0)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.DEFAULT, 2, new Song()), new SongMetadata(1L, SongType.DEFAULT, 3, new Song())), List.of(1, 2, 3), List.of(0, 1, 2)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song())), List.of(1), List.of(0)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), List.of(1, 3), List.of(0, 2))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "resolveMarksInputData")
    void resolveMarks(List<SongMetadata> sourceSongMetadataList, List<Integer> sourceSongTypeMarkResolverMarks, List<Integer> expected) {
        Mockito.when(songTypeMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceSongTypeMarkResolverMarks);

        List<Integer> actual = subtractSongTypeMarkResolver.resolveMarks(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
