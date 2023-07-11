package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreListServiceTest {

    private AlbumGenreProxyService albumGenreProxyService;

    private AlbumGenreListService albumGenreListService;

    @BeforeEach
    void init() {
        this.albumGenreProxyService = Mockito.mock(AlbumGenreProxyService.class);

        this.albumGenreListService = new AlbumGenreListService(albumGenreProxyService);
    }

    static Stream<Arguments> updateCheckAlbumGenresInputData() {
        List<AlbumGenre> sourceAlbumGenreListWithInitialisedIdFields = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithInitialisedIdFieldsExpected = List.of();

        List<AlbumGenre> sourceAlbumGenreListWithOneNullIdField = List.of(new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithOneNullIdFieldExpected = List.of(new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_1"), new Album()));

        List<AlbumGenre> sourceAlbumGenreListWithNullIdFields = List.of(new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithNullIdFieldsExpected = List.of(new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_2"), new Album()));

        return Stream.of(
                Arguments.of(sourceAlbumGenreListWithInitialisedIdFields, sourceAlbumGenreListWithInitialisedIdFieldsExpected),
                Arguments.of(sourceAlbumGenreListWithOneNullIdField, sourceAlbumGenreListWithOneNullIdFieldExpected),
                Arguments.of(sourceAlbumGenreListWithNullIdFields, sourceAlbumGenreListWithNullIdFieldsExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "updateCheckAlbumGenresInputData")
    void updateCheckAlbumGenres(List<AlbumGenre> sourceAlbumGenreList, List<AlbumGenre> expectedAlbumGenreList) {
        int sourceMinimalInvocationCount = 0;

        albumGenreListService.update(sourceAlbumGenreList);
        ArgumentCaptor<AlbumGenre> albumGenreArgumentCaptor = ArgumentCaptor.forClass(AlbumGenre.class);
        Mockito.verify(albumGenreProxyService, Mockito.atLeast(sourceMinimalInvocationCount)).create(albumGenreArgumentCaptor.capture());
        List<AlbumGenre> actualAlbumGenreList = albumGenreArgumentCaptor.getAllValues();

        assertEquals(expectedAlbumGenreList, actualAlbumGenreList);
    }

    static Stream<Arguments> updateInputData() {
        List<AlbumGenre> sourceAlbumGenreListWithInitialisedIdFields = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithInitialisedIdFieldsExpected = List.of();

        List<AlbumGenre> sourceAlbumGenreListWithOneNullIdField = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithOneNullIdFieldExpected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()));

        List<AlbumGenre> sourceAlbumGenreListWithNullIdFields = List.of(new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(null, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListWithNullIdFieldsExpected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));

        return Stream.of(
                Arguments.of(sourceAlbumGenreListWithInitialisedIdFields, sourceAlbumGenreListWithInitialisedIdFieldsExpected),
                Arguments.of(sourceAlbumGenreListWithOneNullIdField, sourceAlbumGenreListWithOneNullIdFieldExpected),
                Arguments.of(sourceAlbumGenreListWithNullIdFields, sourceAlbumGenreListWithNullIdFieldsExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "updateInputData")
    void update(List<AlbumGenre> sourceAlbumGenreList, List<AlbumGenre> expected) {
        AlbumGenre sourceFirstAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album());
        AlbumGenre sourceSecondAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album());
        Mockito.when(albumGenreProxyService.create(Mockito.any())).thenReturn(sourceFirstAlbumGenre).thenReturn(sourceSecondAlbumGenre);

        List<AlbumGenre> actual = albumGenreListService.update(sourceAlbumGenreList);

        assertEquals(expected, actual);
    }

}
