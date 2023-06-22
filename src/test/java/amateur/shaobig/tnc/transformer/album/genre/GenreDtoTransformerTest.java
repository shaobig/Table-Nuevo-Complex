package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreDtoTransformerTest {

    private GenreDtoTransformer genreDtoTransformer;

    @BeforeEach
    void init() {
        this.genreDtoTransformer = new GenreDtoTransformer();
    }

    @Test
    void transform() {
        Genre sourceGenre = new Genre(1L, "GENRE_NAME", false);

        GenreDto actual = genreDtoTransformer.transform(sourceGenre);

        GenreDto expected = new GenreDto(1L, "GENRE_NAME", false);
        assertEquals(expected, actual);
    }

}
