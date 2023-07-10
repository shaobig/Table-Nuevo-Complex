package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreServiceTest {

    private GenreRepository genreRepository;

    private GenreService genreService;

    @BeforeEach
    void init() {
        this.genreRepository = Mockito.mock(GenreRepository.class);

        this.genreService = new GenreService(genreRepository);
    }

    @Test
    void createCheckGenre() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");

        genreService.create(sourceGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreRepository).save(expectedGenre);
    }

    @Test
    void create() {
        Genre sourceRepositoryGenre = new Genre(1L, "GENRE_NAME");
        Genre sourceGenre = new Genre();
        Mockito.when(genreRepository.save(Mockito.any())).thenReturn(sourceRepositoryGenre);

        Genre actual = genreService.create(sourceGenre);

        Genre expected = new Genre(1L, "GENRE_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckName() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");

        genreService.isFound(sourceGenre);

        String expectedName = "GENRE_NAME";
        Mockito.verify(genreRepository).existsByName(expectedName);
    }

    static Stream<Arguments> isFoundInputData() {
        return Stream.of(
                Arguments.of(true, true),
                Arguments.of(false, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(boolean sourceIsFound, boolean expected) {
        Genre sourceGenre = new Genre();
        Mockito.when(genreRepository.existsByName(Mockito.any())).thenReturn(sourceIsFound);

        boolean actual = genreService.isFound(sourceGenre);

        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckName() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME");

        genreService.merge(sourceGenre);

        String expectedName = "GENRE_NAME";
        Mockito.verify(genreRepository).findByName(expectedName);
    }

    @Test
    void merge() {
        Genre sourceRepositoryGenre = new Genre(1L, "GENRE_NAME");
        Genre sourceGenre = new Genre();
        Mockito.when(genreRepository.findByName(Mockito.any())).thenReturn(sourceRepositoryGenre);

        Genre actual = genreService.merge(sourceGenre);

        Genre expected = new Genre(1L, "GENRE_NAME");
        assertEquals(expected, actual);
    }

}
