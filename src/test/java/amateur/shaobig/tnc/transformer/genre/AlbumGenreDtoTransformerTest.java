package amateur.shaobig.tnc.transformer.genre;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumGenreDtoTransformerTest {

    private GenreDtoTransformer genreDtoTransformer;

    private AlbumGenreDtoTransformer albumGenreDtoTransformer;

    @BeforeEach
    void init() {
        this.genreDtoTransformer = Mockito.mock(GenreDtoTransformer.class);

        this.albumGenreDtoTransformer = new AlbumGenreDtoTransformer(genreDtoTransformer);
    }

    @Test
    void transformCheckGenre() {
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME"), new Album());

        albumGenreDtoTransformer.transform(sourceAlbumGenre);

        Genre expectedGenre = new Genre(1L, "GENRE_NAME");
        Mockito.verify(genreDtoTransformer).transform(expectedGenre);
    }

    @Test
    void transform() {
        GenreDto sourceGenreDto = new GenreDto(1L, "GENRE_NAME");
        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, true, new Genre(), new Album());
        Mockito.when(genreDtoTransformer.transform(Mockito.any())).thenReturn(sourceGenreDto);

        AlbumGenreDto actual = albumGenreDtoTransformer.transform(sourceAlbumGenre);

        AlbumGenreDto expected = new AlbumGenreDto(1L, true, new GenreDto(1L, "GENRE_NAME"));
        assertEquals(expected, actual);
    }

}
