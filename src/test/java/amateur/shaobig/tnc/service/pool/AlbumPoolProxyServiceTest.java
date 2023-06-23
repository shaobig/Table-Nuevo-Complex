package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.artist.ArtistProxyService;
import amateur.shaobig.tnc.service.location.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumPoolProxyServiceTest {

    private AlbumPoolService albumPoolService;
    private LocationService locationService;
    private ArtistProxyService artistProxyService;

    private AlbumPoolProxyService albumPoolProxyService;

    @BeforeEach
    void init() {
        this.albumPoolService = Mockito.mock(AlbumPoolService.class);
        this.locationService = Mockito.mock(LocationService.class);
        this.artistProxyService = Mockito.mock(ArtistProxyService.class);

        this.albumPoolProxyService = new AlbumPoolProxyService(albumPoolService, locationService, artistProxyService);
    }

    @Test
    void createArtistIsFound() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of());
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), sourceArtist, List.of(), List.of()));

        albumPoolProxyService.create(sourceAlbumPool);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of());
        Mockito.verify(artistProxyService).isFound(expectedArtist);
    }

    @Test
    void createLocationIsFound() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), sourceArtist, List.of(), List.of()));
        Mockito.when(artistProxyService.isFound(Mockito.any())).thenReturn(false);

        albumPoolProxyService.create(sourceAlbumPool);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).isFound(expectedLocation);
    }

    @Test
    void createMergeArtist() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), sourceArtist, List.of(), List.of()));
        Mockito.when(artistProxyService.isFound(Mockito.any())).thenReturn(true);

        albumPoolProxyService.create(sourceAlbumPool);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Mockito.verify(artistProxyService).merge(expectedArtist);
    }

    static Stream<Arguments> createInputData() {
        AlbumPool albumPoolArtistNotFoundCreated = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool albumPoolArtistNotFoundExpected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        AlbumPool albumPoolArtistNotFoundAndLocationFoundCreated = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool albumPoolArtistNotFoundAndLocationFoundExpected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        AlbumPool albumPoolArtistFoundCreated = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool albumPoolArtistFoundExpected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        return Stream.of(
                Arguments.of(false, false, albumPoolArtistNotFoundCreated, albumPoolArtistNotFoundExpected),
                Arguments.of(false, true, albumPoolArtistNotFoundAndLocationFoundCreated, albumPoolArtistNotFoundAndLocationFoundExpected),
                Arguments.of(true, true, albumPoolArtistFoundCreated, albumPoolArtistFoundExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "createInputData")
    void create(boolean isArtistFound, boolean isLocationFound, AlbumPool sourceCreatedAlbumPool, AlbumPool expected) {
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()), List.of(), List.of()));
        Mockito.when(artistProxyService.isFound(Mockito.any())).thenReturn(isArtistFound);
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(isLocationFound);
        Mockito.when(albumPoolService.create(Mockito.any())).thenReturn(sourceCreatedAlbumPool);

        AlbumPool actual = albumPoolProxyService.create(sourceAlbumPool);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> readAllInputData() {
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListSource = List.of();
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListExpected = List.of();

        List<ReadAllAlbumPoolDto> filledAlbumPoolListSource = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME","COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));
        List<ReadAllAlbumPoolDto> filledAlbumPoolListExpected = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));

        return Stream.of(
                Arguments.of(emptyAlbumPoolListSource, emptyAlbumPoolListExpected),
                Arguments.of(filledAlbumPoolListSource, filledAlbumPoolListExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInputData")
    void readAll(List<ReadAllAlbumPoolDto> sourceAlbums, List<ReadAllAlbumPoolDto> expected) {
        Mockito.when(albumPoolService.readAll()).thenReturn(sourceAlbums);

        List<ReadAllAlbumPoolDto> actual = albumPoolProxyService.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void deleteCheckId() {
        Optional<AlbumPool> sourceAlbumPool = Optional.of(new AlbumPool());
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        albumPoolProxyService.delete(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumPoolService).delete(expectedId);
    }

    @Test
    void deleteAlbumPoolNotFound() {
        Optional<AlbumPool> sourceAlbumPool = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        assertThrows(EntityNotFoundException.class, () -> albumPoolProxyService.delete(sourceId));
    }

    @Test
    void delete() {
        Optional<AlbumPool> sourceAlbumPool = Optional.of(new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        AlbumPool actual = albumPoolProxyService.delete(sourceId);

        AlbumPool expected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

}
