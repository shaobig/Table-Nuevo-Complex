package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.transformer.genre.AlbumGenreDtoTransformer;
import amateur.shaobig.tnc.transformer.genre.GenreDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumAlbumAlbumGenreDtoTransformerTest {

    private GenreDtoTransformer genreDtoTransformer;

    private AlbumGenreDtoTransformer albumGenreDtoTransformer;

    @BeforeEach
    void init() {
        this.genreDtoTransformer = Mockito.mock(GenreDtoTransformer.class);

        this.albumGenreDtoTransformer = new AlbumGenreDtoTransformer(genreDtoTransformer);
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
