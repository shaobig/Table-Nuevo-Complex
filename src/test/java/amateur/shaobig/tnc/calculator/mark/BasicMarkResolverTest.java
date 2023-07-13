package amateur.shaobig.tnc.calculator.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicMarkResolverTest {

    private SongTypeMarkResolver songTypeMarkResolver;
    private SubtractSongTypeMarkResolver subtractSongTypeMarkResolver;

    private BasicMarkResolver basicMarkResolver;

    @BeforeEach
    void init() {
        this.songTypeMarkResolver = Mockito.mock(SongTypeMarkResolver.class);
        this.subtractSongTypeMarkResolver = Mockito.mock(SubtractSongTypeMarkResolver.class);

        this.basicMarkResolver = new BasicMarkResolver(songTypeMarkResolver, subtractSongTypeMarkResolver);
    }

    static Stream<Arguments> resolveMarksInputData() {
        return Stream.of(
                Arguments.of(List.of(1), List.of(), List.of(1)),
                Arguments.of(List.of(1, 2, 3), List.of(), List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2), List.of(2), List.of(1, 2, 2)),
                Arguments.of(List.of(1), List.of(1, 2), List.of(1, 1, 2)),
                Arguments.of(List.of(), List.of(0, 1, 2), List.of(0, 1, 2)),
                Arguments.of(List.of(1), List.of(2), List.of(1, 2)),
                Arguments.of(List.of(1, 2), List.of(), List.of(1, 2)),
                Arguments.of(List.of(), List.of(2), List.of(2)),
                Arguments.of(List.of(), List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource(value = "resolveMarksInputData")
    void resolveMarks(List<Integer> sourceSongTypeMarkResolverMarks, List<Integer> sourceSubtractSongTypeMarkResolverMarks, List<Integer> expected) {
        List<SongMetadata> sourceSongMetadataList = List.of();
        Mockito.when(songTypeMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceSongTypeMarkResolverMarks);
        Mockito.when(subtractSongTypeMarkResolver.resolveMarks(Mockito.anyList())).thenReturn(sourceSubtractSongTypeMarkResolverMarks);

        List<Integer> actual = basicMarkResolver.resolveMarks(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
