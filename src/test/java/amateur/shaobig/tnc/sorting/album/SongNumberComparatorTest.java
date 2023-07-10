package amateur.shaobig.tnc.sorting.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongNumberComparatorTest {

    private SongNumberComparator songNumberComparator;

    @BeforeEach
    void init() {
        this.songNumberComparator = new SongNumberComparator();
    }

    static Stream<Arguments> compareInputData() {
        Song sourceSongWithTheSameNumberLeft = new Song(1L, 0, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Song sourceSongWithTheSameNumberRight = new Song(1L, 0, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        Song sourceSongInAscendantOrderLeft = new Song(1L, 0, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Song sourceSongInAscendantOrderRight = new Song(1L, 1, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        Song sourceSongInDescendantOrderLeft = new Song(1L, 1, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Song sourceSongInDescendantOrderRight = new Song(1L, 0, "", new SongMetadata(), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        return Stream.of(
                Arguments.of(sourceSongWithTheSameNumberLeft, sourceSongWithTheSameNumberRight, 0),
                Arguments.of(sourceSongInAscendantOrderLeft, sourceSongInAscendantOrderRight, -1),
                Arguments.of(sourceSongInDescendantOrderLeft, sourceSongInDescendantOrderRight, 1)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "compareInputData")
    void compare(Song sourceSongLeft, Song sourceSongRight, int expected) {
        int actual = songNumberComparator.compare(sourceSongLeft, sourceSongRight);

        assertEquals(expected, actual);
    }

}
