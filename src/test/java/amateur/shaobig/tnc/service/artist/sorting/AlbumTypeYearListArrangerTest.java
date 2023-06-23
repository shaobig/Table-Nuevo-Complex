package amateur.shaobig.tnc.service.artist.sorting;

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

class AlbumTypeYearListArrangerTest {

    private AlbumTypeYearListArranger albumTypeYearListArranger;

    @BeforeEach
    void init() {
        this.albumTypeYearListArranger = new AlbumTypeYearListArranger();
    }

    static Stream<Arguments> arrangeInputData() {
        List<Album> emptyAlbumList = List.of();
        List<Album> emptyAlbumListExpected = List.of();

        List<Album> albumListArrangedByTypeInAscendantOrder = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTypeInAscendantOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListArrangedByTypeInDescendantOrder = List.of(new Album(1L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTypeInDescendantOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListArrangedByTypeInMixedOrder = List.of(new Album(1L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTypeInMixedOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListArrangedByTheSameTypeAndYearInAscendantOrder = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTheSameTypeAndYearInAscendantOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 0, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.COVER, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListArrangedByTheSameTypeAndYearInDescendantOrder = List.of(new Album(1L, 0, "", 2, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTheSameTypeAndYearInDescendantOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 2, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListArrangedByTheSameTypeAndYearInMixedOrder = List.of(new Album(1L, 0, "", 2, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListArrangedByTheSameTypeAndYearInMixedOrderExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 2, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        List<Album> albumListWithMatchedYear = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        List<Album> albumListWithMatchedYearExpected = List.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(2L, 0, "", 1, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(3L, 0, "", 1, AlbumType.EP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        return Stream.of(
                Arguments.of(emptyAlbumList, emptyAlbumListExpected),
                Arguments.of(albumListArrangedByTypeInAscendantOrder, albumListArrangedByTypeInAscendantOrderExpected),
                Arguments.of(albumListArrangedByTypeInDescendantOrder, albumListArrangedByTypeInDescendantOrderExpected),
                Arguments.of(albumListArrangedByTypeInMixedOrder, albumListArrangedByTypeInMixedOrderExpected),
                Arguments.of(albumListArrangedByTheSameTypeAndYearInAscendantOrder, albumListArrangedByTheSameTypeAndYearInAscendantOrderExpected),
                Arguments.of(albumListArrangedByTheSameTypeAndYearInDescendantOrder, albumListArrangedByTheSameTypeAndYearInDescendantOrderExpected),
                Arguments.of(albumListArrangedByTheSameTypeAndYearInMixedOrder, albumListArrangedByTheSameTypeAndYearInMixedOrderExpected),
                Arguments.of(albumListArrangedByTheSameTypeAndYearInMixedOrder, albumListArrangedByTheSameTypeAndYearInMixedOrderExpected),
                Arguments.of(albumListWithMatchedYear, albumListWithMatchedYearExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "arrangeInputData")
    void arrange(List<Album> sourceAlbums, List<Album> expected) {
        List<Album> actual = albumTypeYearListArranger.arrange(sourceAlbums);

        assertEquals(expected, actual);
    }

}
