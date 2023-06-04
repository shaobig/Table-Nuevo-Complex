package amateur.shaobig.tnc.service.artist.sorting;

import amateur.shaobig.tnc.entity.Album;
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

        List<Album> albumListContainingAlbumTypeInAscendingOrder = List.of(new Album("", 0, AlbumType.LP),
                new Album("", 0, AlbumType.EP), new Album("", 0, AlbumType.COMPILATION));
        List<Album> albumListContainingAlbumTypeInAscendingOrderExpected = List.of(new Album("", 0, AlbumType.LP),
                new Album("", 0, AlbumType.EP), new Album("", 0, AlbumType.COMPILATION));

        List<Album> albumListContainingAlbumTypeInDescendingOrder = List.of(new Album("", 0, AlbumType.COMPILATION),
                new Album("", 0, AlbumType.EP), new Album("", 0, AlbumType.LP));
        List<Album> albumListContainingAlbumTypeInDescendingOrderExpected = List.of(new Album("", 0, AlbumType.LP),
                new Album("", 0, AlbumType.EP), new Album("", 0, AlbumType.COMPILATION));

        List<Album> albumListContainingYearAndAlbumTypeInAscendingOrder = List.of(new Album("", 1, AlbumType.LP),
                new Album("", 2, AlbumType.EP), new Album("", 3, AlbumType.COMPILATION));
        List<Album> albumListContainingYearAndAlbumTypeInAscendingOrderExpected = List.of(new Album("", 1, AlbumType.LP),
                new Album("", 2, AlbumType.EP), new Album("", 3, AlbumType.COMPILATION));

        List<Album> albumListContainingYearAndAlbumTypeInDescendingOrder = List.of(new Album("", 3, AlbumType.COMPILATION),
                new Album("", 2, AlbumType.EP), new Album("", 1, AlbumType.LP));
        List<Album> albumListContainingYearAndAlbumTypeInDescendingOrderExpected = List.of(new Album("", 1, AlbumType.LP),
                new Album("", 2, AlbumType.EP), new Album("", 3, AlbumType.COMPILATION));

        List<Album> albumListContainingTheSameYearTwice = List.of(new Album("", 1, AlbumType.EP),
                new Album("", 2, AlbumType.LP), new Album("", 1, AlbumType.COMPILATION));
        List<Album> albumListContainingTheSameYearTwiceExpected = List.of(new Album("", 2, AlbumType.LP),
                new Album("", 1, AlbumType.EP), new Album("", 1, AlbumType.COMPILATION));

        return Stream.of(
                Arguments.of(emptyAlbumList, emptyAlbumListExpected),
                Arguments.of(albumListContainingAlbumTypeInAscendingOrder, albumListContainingAlbumTypeInAscendingOrderExpected),
                Arguments.of(albumListContainingAlbumTypeInDescendingOrder, albumListContainingAlbumTypeInDescendingOrderExpected),
                Arguments.of(albumListContainingYearAndAlbumTypeInAscendingOrder, albumListContainingYearAndAlbumTypeInAscendingOrderExpected),
                Arguments.of(albumListContainingYearAndAlbumTypeInDescendingOrder, albumListContainingYearAndAlbumTypeInDescendingOrderExpected),
                Arguments.of(albumListContainingTheSameYearTwice, albumListContainingTheSameYearTwiceExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "arrangeInputData")
    void arrange(List<Album> sourceAlbums, List<Album> expected) {
        List<Album> actual = albumTypeYearListArranger.arrange(sourceAlbums);

        assertEquals(expected, actual);
    }

}
