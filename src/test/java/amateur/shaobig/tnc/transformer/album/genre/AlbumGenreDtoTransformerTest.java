//package amateur.shaobig.tnc.transformer.album.genre;
//
//import amateur.shaobig.tnc.dto.genre.GenreDto;
//import amateur.shaobig.tnc.entity.Album;
//import amateur.shaobig.tnc.entity.AlbumGenre;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class AlbumGenreDtoTransformerTest {
//
//    private GenreDtoTransformer genreDtoTransformer;
//
//    @BeforeEach
//    void init() {
//        this.genreDtoTransformer = new GenreDtoTransformer();
//    }
//
//    @Test
//    void transform() {
//        AlbumGenre sourceAlbumGenre = new AlbumGenre(1L, "GENRE_NAME", false, new Album());
//
//        GenreDto actual = genreDtoTransformer.transform(sourceAlbumGenre);
//
//        GenreDto expected = new GenreDto(1L, "GENRE_NAME", false);
//        assertEquals(expected, actual);
//    }
//
//}
