package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreListProxyServiceTest {

    private AlbumGenreListService albumGenreListService;
    private AlbumGenreProxyService albumGenreProxyService;

    private AlbumGenreListProxyService albumGenreListProxyService;

    @BeforeEach
    void init() {
        this.albumGenreListService = Mockito.mock(AlbumGenreListService.class);
        this.albumGenreProxyService = Mockito.mock(AlbumGenreProxyService.class);

        this.albumGenreListProxyService = new AlbumGenreListProxyService(albumGenreListService, albumGenreProxyService);
    }

    @Test
    void createCheckAlbumGenres() {
        List<AlbumGenre> sourceAlbumGenreList = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));

        albumGenreListProxyService.create(sourceAlbumGenreList);
        ArgumentCaptor<AlbumGenre> albumGenreArgumentCaptor = ArgumentCaptor.forClass(AlbumGenre.class);
        Mockito.verify(albumGenreProxyService, Mockito.atLeastOnce()).create(albumGenreArgumentCaptor.capture());
        List<AlbumGenre> actualAlbumGenreList = albumGenreArgumentCaptor.getAllValues();

        List<AlbumGenre> expectedAlbumGenreList = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        assertEquals(expectedAlbumGenreList, actualAlbumGenreList);
    }

    @Test
    void create() {
        AlbumGenre sourceFirstAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album());
        AlbumGenre sourceSecondAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album());
        List<AlbumGenre> sourceAlbumGenreList = List.of(new AlbumGenre(), new AlbumGenre());
        Mockito.when(albumGenreProxyService.create(Mockito.any())).thenReturn(sourceFirstAlbumGenre).thenReturn(sourceSecondAlbumGenre);

        List<AlbumGenre> actual = albumGenreListProxyService.create(sourceAlbumGenreList);

        List<AlbumGenre> expected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        assertEquals(expected, actual);
    }

    @Test
    void updateCheckAlbumGenres() {
        List<AlbumGenre> sourceAlbumGenreList = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));

        albumGenreListProxyService.update(sourceAlbumGenreList);

        List<AlbumGenre> expectedAlbumGenreList = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        Mockito.verify(albumGenreListService).update(expectedAlbumGenreList);
    }

    static Stream<Arguments> updateInputData() {
        List<AlbumGenre> sourceAlbumGenreListWithInitialisedIdFields = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListContainingOneElement = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()));
        List<AlbumGenre> sourceAlbumGenreListEmpty = List.of();

        return Stream.of(
                Arguments.of(sourceAlbumGenreListWithInitialisedIdFields),
                Arguments.of(sourceAlbumGenreListContainingOneElement),
                Arguments.of(sourceAlbumGenreListEmpty)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "updateInputData")
    void update(List<AlbumGenre> sourceCreatedAlbumGenreList) {
        List<AlbumGenre> sourceAlbumGenreList = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        Mockito.when(albumGenreListService.update(Mockito.anyList())).thenReturn(sourceCreatedAlbumGenreList);

        List<AlbumGenre> actual = albumGenreListProxyService.update(sourceAlbumGenreList);

        List<AlbumGenre> expected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        assertEquals(expected, actual);
    }

}
