package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreDtoTransformerTest {

    private GenreDtoTransformer genreDtoTransformer;

    @BeforeEach
    void init() {
        this.genreDtoTransformer = new GenreDtoTransformer();
    }

    @Test
    void transform() {
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME"));

        GenreDto actual = genreDtoTransformer.transform(sourceAlbumGenre);

        GenreDto expected = new GenreDto(1L, "GENRE_NAME", true);
        assertEquals(expected, actual);
    }

}
