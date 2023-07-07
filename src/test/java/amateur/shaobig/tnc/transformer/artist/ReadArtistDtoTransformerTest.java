package amateur.shaobig.tnc.transformer.artist;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.transformer.album.ReadAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.artist.location.LocationDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadArtistDtoTransformerTest {

    private LocationDtoTransformer locationDtoTransformer;
    private ReadAlbumDtoTransformer readAlbumDtoTransformer;

    private ReadArtistDtoTransformer readArtistDtoTransformer;

    @BeforeEach
    void init() {
        this.locationDtoTransformer = Mockito.mock(LocationDtoTransformer.class);
        this.readAlbumDtoTransformer = Mockito.mock(ReadAlbumDtoTransformer.class);

        this.readArtistDtoTransformer = new ReadArtistDtoTransformer(locationDtoTransformer, readAlbumDtoTransformer);
    }

    @Test
    void transformCheckLocationDtoTransformer() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        readArtistDtoTransformer.transform(sourceArtist);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationDtoTransformer).transform(expectedLocation);
    }

    @Test
    void transformReadAlbumDtoTransformer() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(), List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));

        readArtistDtoTransformer.transform(sourceArtist);
        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
        Mockito.verify(readAlbumDtoTransformer, Mockito.atLeastOnce()).transform(albumArgumentCaptor.capture());
        List<Album> actual = albumArgumentCaptor.getAllValues();

        List<Album> expected = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

    @Test
    void transform() {
        LocationDto sourceLocationDto = new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        ReadAlbumDto sourceFirstReadAlbumDto = new ReadAlbumDto(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of());
        ReadAlbumDto sourceSecondReadAlbumDto = new ReadAlbumDto(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of());
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(), List.of(new Album(), new Album()));
        Mockito.when(locationDtoTransformer.transform(Mockito.any())).thenReturn(sourceLocationDto);
        Mockito.when(readAlbumDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstReadAlbumDto).thenReturn(sourceSecondReadAlbumDto);

        ReadArtistDto actual = readArtistDtoTransformer.transform(sourceArtist);

        ReadArtistDto expected = new ReadArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of(new ReadAlbumDto(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of()), new ReadAlbumDto(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of())));
        assertEquals(expected, actual);
    }

}
