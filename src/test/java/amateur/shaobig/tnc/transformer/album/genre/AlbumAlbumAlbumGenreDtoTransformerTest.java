package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumAlbumAlbumGenreDtoTransformerTest {

    private AlbumGenreDtoTransformer albumGenreDtoTransformer;

    @BeforeEach
    void init() {
        this.albumGenreDtoTransformer = new AlbumGenreDtoTransformer();
    }

    @Test
    void transform() {
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME"), new Album());

        AlbumGenreDto actual = albumGenreDtoTransformer.transform(sourceAlbumGenre);

        AlbumGenreDto expected = new AlbumGenreDto(1L, "GENRE_NAME", true);
        assertEquals(expected, actual);
    }

}
