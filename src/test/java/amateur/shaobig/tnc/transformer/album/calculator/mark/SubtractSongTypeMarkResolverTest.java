package amateur.shaobig.tnc.transformer.album.calculator.mark;

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
                Arguments.of(List.of(), List.of(), List.of()),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1)), List.of(1), List.of(0)),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.DEFAULT, 2), new SongMetadata(SongType.DEFAULT, 3)), List.of(1, 2, 3), List.of(0, 1, 2)),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.DEFAULT, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(1, 2), List.of(0, 1)),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.INSTRUMENTAL, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(), List.of()),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.COVER, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(), List.of())
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
