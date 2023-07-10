package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.location.LocationProxyService;
import amateur.shaobig.tnc.sorting.ComparatorListArranger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArtistProxyServiceTest {

    private ArtistService artistService;
    private LocationProxyService locationProxyService;
    private ComparatorListArranger<Album> albumComparatorListArranger;

    private ArtistProxyService artistProxyService;

    @BeforeEach
    void init() {
        this.artistService = Mockito.mock(ArtistService.class);
        this.locationProxyService = Mockito.mock(LocationProxyService.class);
        this.albumComparatorListArranger = Mockito.mock(ComparatorListArranger.class);

        this.artistProxyService = new ArtistProxyService(artistService, locationProxyService, albumComparatorListArranger);
    }

    @Test
    void createCheckLocation() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistProxyService.create(sourceArtist);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationProxyService).create(expectedLocation);
    }

    @Test
    void createIsFound() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(), List.of());
        Mockito.when(locationProxyService.create(Mockito.any())).thenReturn(sourceLocation);

        artistProxyService.create(sourceArtist);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Mockito.verify(artistService).isFound(expectedArtist);
    }

    @Test
    void createMerge() {
        boolean sourceIsFound = true;
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(), List.of());
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(locationProxyService.create(Mockito.any())).thenReturn(sourceLocation);

        artistProxyService.create(sourceArtist);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Mockito.verify(artistService).merge(expectedArtist);
    }

    @Test
    void createCheckArtist() {
        boolean sourceIsFound = false;
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(), List.of());
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(locationProxyService.create(Mockito.any())).thenReturn(sourceLocation);

        artistProxyService.create(sourceArtist);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Mockito.verify(artistService).create(expectedArtist);
    }

    @Test
    void createWhileArtistIsFound() {
        boolean sourceIsFound = true;
        Artist sourceRepositoryArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Artist sourceArtist = new Artist();
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(artistService.merge(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistProxyService.create(sourceArtist);

        Artist expected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        assertEquals(expected, actual);
    }

    @Test
    void createWhileArtistIsNotFound() {
        boolean sourceIsFound = false;
        Artist sourceRepositoryArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Artist sourceArtist = new Artist();
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(artistService.create(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistProxyService.create(sourceArtist);

        Artist expected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        assertEquals(expected, actual);
    }

    @Test
    void readCheckId() {
        Optional<Artist> sourceOptionalArtist = Optional.of(new Artist());
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceOptionalArtist);

        artistProxyService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(artistService).read(expectedId);
    }

    @Test
    void readCheckAlbums() {
        Optional<Artist> sourceArtist = Optional.of(new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        artistProxyService.read(sourceId);

        List<Album> expectedAlbums = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumComparatorListArranger).arrange(expectedAlbums);
    }

    @Test
    void readArtistIsNotFound() {
        Optional<Artist> sourceArtist = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        assertThrows(EntityNotFoundException.class, () -> artistProxyService.read(sourceId));
    }

    static Stream<Arguments> readInputData() {
        Optional<Artist> artistWithEmptyNames = Optional.of(new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of()));
        Artist artistWithEmptyNamesExpected = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "", "", ""), List.of());

        Optional<Artist> artistWithNameAndLocation = Optional.of(new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of()));
        Artist artistWithNameAndLocationExpected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        return Stream.of(
                Arguments.of(artistWithEmptyNames, artistWithEmptyNamesExpected),
                Arguments.of(artistWithNameAndLocation, artistWithNameAndLocationExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readInputData")
    void read(Optional<Artist> sourceArtist, Artist expected) {
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        Artist actual = artistProxyService.read(sourceId);

        assertEquals(expected, actual);
    }

}
