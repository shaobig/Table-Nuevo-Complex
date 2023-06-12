package amateur.shaobig.tnc.transformer.album.calculator.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllSongMarkResolverTest {

    private AllSongMarkResolver allSongMarkResolver;

    @BeforeEach
    void init() {
        this.allSongMarkResolver = new AllSongMarkResolver();
    }

    static Stream<Arguments> resolveMarksInputData() {
        return Stream.of(
                Arguments.of(List.of(), List.of()),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1)), List.of(1)),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.DEFAULT, 2), new SongMetadata(SongType.DEFAULT, 3)), List.of(1, 2, 3)),
                Arguments.of(List.of(new SongMetadata(SongType.DEFAULT, 1), new SongMetadata(SongType.DEFAULT, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(1, 2, 3)),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.INSTRUMENTAL, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(1, 2, 3)),
                Arguments.of(List.of(new SongMetadata(SongType.INSTRUMENTAL, 1), new SongMetadata(SongType.COVER, 2), new SongMetadata(SongType.INSTRUMENTAL, 3)), List.of(1, 2, 3))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "resolveMarksInputData")
    void resolveMarks(List<SongMetadata> sourceSongMetadataList, List<Integer> expected) {
        List<Integer> actual = allSongMarkResolver.resolveMarks(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}