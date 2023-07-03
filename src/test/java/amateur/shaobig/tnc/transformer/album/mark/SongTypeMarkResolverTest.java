package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongTypeMarkResolverTest {

    private SongType songType;

    private SongTypeMarkResolver songTypeMarkResolver;

    @BeforeEach
    void init() {
        this.songType = SongType.DEFAULT;

        this.songTypeMarkResolver = new SongTypeMarkResolver(songType);
    }

    static Stream<Arguments> resolveMarksInputData() {
        return Stream.of(
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song())), List.of(1)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.DEFAULT, 2, new Song()), new SongMetadata(1L, SongType.DEFAULT, 3, new Song())), List.of(1, 2, 3)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.DEFAULT, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), List.of(1, 2)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.COVER, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), List.of(1)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.COVER, 1, new Song()), new SongMetadata(1L, SongType.COVER, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), List.of()),
                Arguments.of(List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song()), new SongMetadata(1L, SongType.COVER, 3, new Song())), List.of(1)),
                Arguments.of(List.of(new SongMetadata(1L, SongType.INSTRUMENTAL, 1, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 2, new Song()), new SongMetadata(1L, SongType.INSTRUMENTAL, 3, new Song())), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource(value = "resolveMarksInputData")
    void resolveMarks(List<SongMetadata> sourceSongMetadataList, List<Integer> expected) {
        List<Integer> actual = songTypeMarkResolver.resolveMarks(sourceSongMetadataList);

        assertEquals(expected, actual);
    }

}
