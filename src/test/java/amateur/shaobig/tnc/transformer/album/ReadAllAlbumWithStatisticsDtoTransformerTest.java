package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumsDto;
import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.transformer.album.genre.AlbumGenreDtoTransformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.album.statistics.AlbumStatisticsDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadAllAlbumsDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private AlbumStatisticsDtoTransformer albumStatisticsDtoTransformer;
    private AlbumGenreDtoTransformer albumGenreDtoTransformer;

    private ReadAllAlbumsDtoTransformer readAllAlbumsDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);
        this.albumStatisticsDtoTransformer = Mockito.mock(AlbumStatisticsDtoTransformer.class);
        this.albumGenreDtoTransformer = Mockito.mock(AlbumGenreDtoTransformer.class);

        this.readAllAlbumsDtoTransformer = new ReadAllAlbumsDtoTransformer(albumMetadataDtoTransformer, albumStatisticsDtoTransformer, albumGenreDtoTransformer);
    }

    @Test
    void transformCheckAlbumStatisticsDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()), List.of(), List.of());

        readAllAlbumsDtoTransformer.transform(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()), List.of(), List.of());
        Mockito.verify(albumStatisticsDtoTransformer).transform(expectedAlbum);
    }

    @Test
    void transform() {
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        AlbumStatisticsDto sourceAlbumStatisticsDto = new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        AlbumGenreDto sourceFirstAlbumGenreDto = new AlbumGenreDto(1L, "GENRE_NAME_1", true);
        AlbumGenreDto sourceSecondAlbumGenreDto = new AlbumGenreDto(1L, "GENRE_NAME_2", true);
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of()), List.of(new AlbumGenre(), new AlbumGenre()), List.of());
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);
        Mockito.when(albumStatisticsDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumStatisticsDto);
        Mockito.when(albumGenreDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstAlbumGenreDto).thenReturn(sourceSecondAlbumGenreDto);

        ReadAllAlbumsDto actual = readAllAlbumsDtoTransformer.transform(sourceAlbum);

        ReadAllAlbumsDto expected = new ReadAllAlbumsDto(1L, "ALBUM_NAME", AlbumType.LP, 0, 1L, "ARTIST_NAME", "COUNTRY_NAME", new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), List.of(new AlbumGenreDto(1L, "GENRE_NAME_1", true), new AlbumGenreDto(1L, "GENRE_NAME_2", true)));
        assertEquals(expected, actual);
    }

}
