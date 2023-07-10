package amateur.shaobig.tnc.sorting.artist;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumTypeAndYearComparatorTest {

    private AlbumTypeAndYearComparator albumTypeAndYearComparator;

    @BeforeEach
    void init() {
        this.albumTypeAndYearComparator = new AlbumTypeAndYearComparator();
    }

    static Stream<Arguments> compareInputData() {
        Album sourceAlbumWithTheSameAlbumTypeAndYearLeft = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWithTheSameAlbumTypeAndYearRight = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderLeft = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderRight = new Album(1L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderLeft = new Album(1L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderRight = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereYearValueInAscendantOrderLeft = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereYearValueInAscendantOrderRight = new Album(1L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereYearValueInDescendantOrderLeft = new Album(1L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereYearValueInDescendantOrderRight = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderAndYearValueInDescendantOrderLeft = new Album(1L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderAndYearValueInDescendantOrderRight = new Album(1L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        Album sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderAndYearValueInAscendantOrderLeft = new Album(1L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderAndYearValueInAscendantOrderRight = new Album(1L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        return Stream.of(
                Arguments.of(sourceAlbumWithTheSameAlbumTypeAndYearLeft, sourceAlbumWithTheSameAlbumTypeAndYearRight, 0),
                Arguments.of(sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderLeft, sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderRight, -1),
                Arguments.of(sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderLeft, sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderRight, 1),
                Arguments.of(sourceAlbumWhereYearValueInAscendantOrderLeft, sourceAlbumWhereYearValueInAscendantOrderRight, -1),
                Arguments.of(sourceAlbumWhereYearValueInDescendantOrderLeft, sourceAlbumWhereYearValueInDescendantOrderRight, 1),
                Arguments.of(sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderAndYearValueInDescendantOrderLeft, sourceAlbumWhereAlbumTypeOrderValueInAscendantOrderAndYearValueInDescendantOrderRight, -1),
                Arguments.of(sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderAndYearValueInAscendantOrderLeft, sourceAlbumWhereAlbumTypeOrderValueInDescendantOrderAndYearValueInAscendantOrderRight, 1)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "compareInputData")
    void compare(Album sourceAlbumLeft, Album sourceAlbumRight, int expected) {
        int actual = albumTypeAndYearComparator.compare(sourceAlbumLeft, sourceAlbumRight);

        assertEquals(expected, actual);
    }

}
