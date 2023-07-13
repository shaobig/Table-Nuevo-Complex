package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreListProxyServiceTest {

    private AlbumGenreProxyService albumGenreProxyService;

    private AlbumGenreListProxyService albumGenreListProxyService;

    @BeforeEach
    void init() {
        this.albumGenreProxyService = Mockito.mock(AlbumGenreProxyService.class);

        this.albumGenreListProxyService = new AlbumGenreListProxyService(albumGenreProxyService);
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
    void update() {
        AlbumGenre sourceFirstAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album());
        AlbumGenre sourceSecondAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album());
        List<AlbumGenre> sourceAlbumGenreList = List.of(new AlbumGenre(), new AlbumGenre());
        Mockito.when(albumGenreProxyService.update(Mockito.any())).thenReturn(sourceFirstAlbumGenre).thenReturn(sourceSecondAlbumGenre);

        List<AlbumGenre> actual = albumGenreListProxyService.update(sourceAlbumGenreList);

        List<AlbumGenre> expected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        assertEquals(expected, actual);
    }

}
