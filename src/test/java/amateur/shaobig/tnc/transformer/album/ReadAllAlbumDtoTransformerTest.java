package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.transformer.album.statistics.AlbumStatisticsDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadAllAlbumDtoTransformerTest {

    private AlbumStatisticsDtoTransformer albumStatisticsDtoTransformer;

    private ReadAllAlbumDtoTransformer readAllAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.albumStatisticsDtoTransformer = Mockito.mock(AlbumStatisticsDtoTransformer.class);

        this.readAllAlbumDtoTransformer = new ReadAllAlbumDtoTransformer(albumStatisticsDtoTransformer);
    }

    @Test
    void transformCheckAlbumStatisticsDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()), List.of(), List.of());

        readAllAlbumDtoTransformer.transform(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()), List.of(), List.of());
        Mockito.verify(albumStatisticsDtoTransformer).transform(expectedAlbum);
    }

    @Test
    void transform() {
        AlbumStatisticsDto sourceAlbumStatisticsDto = new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of()), List.of(), List.of());
        Mockito.when(albumStatisticsDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumStatisticsDto);

        ReadAllAlbumDto actual = readAllAlbumDtoTransformer.transform(sourceAlbum);

        ReadAllAlbumDto expected = new ReadAllAlbumDto(1L,  "ALBUM_NAME", AlbumType.LP, 0, 1L, "ARTIST_NAME", "COUNTRY_NAME", new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
        assertEquals(expected, actual);
    }

}
