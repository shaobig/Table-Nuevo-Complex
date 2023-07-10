package amateur.shaobig.tnc.transformer.pool;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.DeleteAlbumDto;
import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.DeleteAlbumDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteAlbumPoolDtoTransformerTest {

    private DeleteAlbumDtoTransformer deleteAlbumDtoTransformer;

    private DeleteAlbumPoolDtoTransformer deleteAlbumPoolDtoTransformer;

    @BeforeEach
    void init() {
        this.deleteAlbumDtoTransformer = Mockito.mock(DeleteAlbumDtoTransformer.class);

        this.deleteAlbumPoolDtoTransformer = new DeleteAlbumPoolDtoTransformer(deleteAlbumDtoTransformer);
    }

    @Test
    void transformCheckDeleteAlbumDtoTransformer() {
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCATION_NAME"), List.of()), List.of(), List.of()));

        deleteAlbumPoolDtoTransformer.transform(sourceAlbumPool);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCATION_NAME"), List.of()), List.of(), List.of());
        Mockito.verify(deleteAlbumDtoTransformer).transform(expectedAlbum);
    }

    @Test
    void transform() {
        DeleteAlbumDto sourceDeleteAlbumDto = new DeleteAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new AlbumGenreDto(1L, "GENRE_NAME", false)), List.of(new SongDto(1L, 0, "SONG_NAME", new SongMetadataDto(SongType.DEFAULT, 1))));
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album());
        Mockito.when(deleteAlbumDtoTransformer.transform(Mockito.any())).thenReturn(sourceDeleteAlbumDto);

        DeleteAlbumPoolDto actual = deleteAlbumPoolDtoTransformer.transform(sourceAlbumPool);

        DeleteAlbumPoolDto expected = new DeleteAlbumPoolDto(new DeleteAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new AlbumGenreDto(1L, "GENRE_NAME", false)), List.of(new SongDto(1L, 0, "SONG_NAME", new SongMetadataDto(SongType.DEFAULT, 1)))));
        assertEquals(expected, actual);
    }

}
