package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreProxyServiceTest {

    private GenreService genreService;

    private GenreProxyService genreProxyService;

    @BeforeEach
    void init() {
        this.genreService = Mockito.mock(GenreService.class);

        this.genreProxyService = new GenreProxyService(genreService);
    }

    @Test
    void createCheckIsFound() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");

        genreProxyService.create(sourceGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreService).isFound(expectedGenre);
    }

    @Test
    void createCheckMerge() {
        boolean sourceIsFound = true;
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");
        Mockito.when(genreService.isFound(Mockito.any())).thenReturn(sourceIsFound);

        genreProxyService.create(sourceGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreService).merge(expectedGenre);
    }

    @Test
    void createCheckGenre() {
        boolean sourceIsFound = false;
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");
        Mockito.when(genreService.isFound(Mockito.any())).thenReturn(sourceIsFound);

        genreProxyService.create(sourceGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreService).create(expectedGenre);
    }

    @Test
    void createWhileGenreIsFound() {
        boolean sourceIsFound = true;
        Genre sourceRepositoryGenre = new Genre(1L, "GENRE_NAME");
        Genre sourceGenre = new Genre();
        Mockito.when(genreService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(genreService.merge(Mockito.any())).thenReturn(sourceRepositoryGenre);

        Genre actual = genreProxyService.create(sourceGenre);

        Genre expected = new Genre(1L, "GENRE_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void createWhileGenreIsNotFound() {
        boolean sourceIsFound = false;
        Genre sourceRepositoryGenre = new Genre(1L, "GENRE_NAME");
        Genre sourceGenre = new Genre();
        Mockito.when(genreService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(genreService.create(Mockito.any())).thenReturn(sourceRepositoryGenre);

        Genre actual = genreProxyService.create(sourceGenre);

        Genre expected = new Genre(1L, "GENRE_NAME");
        assertEquals(expected, actual);
    }

}
