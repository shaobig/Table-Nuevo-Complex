package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreProxyServiceTest {

    private GenreProxyService genreProxyService;

    private AlbumGenreProxyService albumGenreProxyService;

    @BeforeEach
    void init() {
        this.genreProxyService = Mockito.mock(GenreProxyService.class);

        this.albumGenreProxyService = new AlbumGenreProxyService(genreProxyService);
    }

    @Test
    void createCheckGenre() {
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME"), new Album());

        albumGenreProxyService.create(sourceAlbumGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreProxyService).create(expectedGenre);
    }

    @Test
    void create() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(), new Album());
        Mockito.when(genreProxyService.create(Mockito.any())).thenReturn(sourceGenre);

        AlbumGenre actual = albumGenreProxyService.create(sourceAlbumGenre);

        AlbumGenre expected = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME"), new Album());
        assertEquals(expected, actual);
    }

}
