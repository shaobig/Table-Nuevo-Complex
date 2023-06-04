package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.artist.sorting.AlbumTypeYearListArranger;
import amateur.shaobig.tnc.service.location.LocationService;
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
    private LocationService locationService;
    private AlbumTypeYearListArranger albumTypeYearListArranger;

    private ArtistProxyService artistProxyService;

    @BeforeEach
    void init() {
        this.artistService = Mockito.mock(ArtistService.class);
        this.locationService = Mockito.mock(LocationService.class);
        this.albumTypeYearListArranger = Mockito.mock(AlbumTypeYearListArranger.class);

        this.artistProxyService = new ArtistProxyService(artistService, locationService, albumTypeYearListArranger);
    }

    @Test
    void readEntityNotFound() {
        Optional<Artist> sourceArtist = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        assertThrows(EntityNotFoundException.class, () -> artistProxyService.read(sourceId));
    }

    @Test
    void readListArrangeInputArgument() {
        Optional<Artist> sourceArtist = Optional.of(new Artist());
        List<Album> sourceAlbums = List.of(new Album("ALBUM_NAME", 0), new Album("ALBUM_NAME_2", 0));
        sourceArtist.get().setAlbums(sourceAlbums);
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        artistProxyService.read(sourceId);

        List<Album> expectedAlbums = List.of(new Album("ALBUM_NAME", 0), new Album("ALBUM_NAME_2", 0));
        Mockito.verify(albumTypeYearListArranger).arrange(expectedAlbums);
    }

    static Stream<Arguments> readInput() {
        Optional<Artist> artistWithEmptyNames = Optional.of(new Artist("", new Location("")));
        Artist artistWithEmptyNamesExpected = new Artist("", new Location(""));

        Optional<Artist> artistWithNameAndLocation = Optional.of(new Artist("ARTIST_NAME", new Location("LOCATION_NAME")));
        Artist artistWithNameAndLocationExpected = new Artist("ARTIST_NAME", new Location("LOCATION_NAME"));

        Optional<Artist> artistWithNameAndLocationAndStatus = Optional.of(new Artist("ARTIST_NAME", new Location("LOCATION_NAME"), ArtistStatus.ACTIVE));
        Artist artistWithNameAndLocationAndStatusExpected = new Artist("ARTIST_NAME", new Location("LOCATION_NAME"), ArtistStatus.ACTIVE);

        return Stream.of(
                Arguments.of(artistWithEmptyNames, artistWithEmptyNamesExpected),
                Arguments.of(artistWithNameAndLocation, artistWithNameAndLocationExpected),
                Arguments.of(artistWithNameAndLocationAndStatus, artistWithNameAndLocationAndStatusExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readInput")
    void read(Optional<Artist> sourceArtist, Artist expected) {
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        Artist actual = artistProxyService.read(sourceId);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> readAllInput() {
        List<Artist> sourceEmptyList = List.of();
        List<Artist> expectedEmptyList = List.of();

        List<Artist> sourceFilledList = List.of(new Artist("ARTIST_NAME", new Location("COUNTRY_NAME")));
        List<Artist> expectedFilledList = List.of(new Artist("ARTIST_NAME", new Location("COUNTRY_NAME")));

        return Stream.of(
                Arguments.of(sourceEmptyList, expectedEmptyList),
                Arguments.of(sourceFilledList, expectedFilledList)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInput")
    void readAll(List<Artist> sourceArtists, List<Artist> expected) {
        Mockito.when(artistService.readAll()).thenReturn(sourceArtists);

        List<Artist> actual = artistProxyService.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckArtist() {
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("LOCATION_NAME"));
        Long sourceId = 1L;
        sourceArtist.setId(sourceId);

        artistProxyService.merge(sourceArtist);

        Artist expectedArtist = new Artist("ARTIST_NAME", new Location("LOCATION_NAME"));
        Mockito.verify(artistService).merge(expectedArtist);
    }

    @Test
    void mergeArtistNotFound() {
        Artist sourceArtist = new Artist();
        Long sourceId = 1L;
        sourceArtist.setId(sourceId);
        Mockito.when(artistService.merge(Mockito.any())).thenThrow(jakarta.persistence.EntityNotFoundException.class);

        assertThrows(jakarta.persistence.EntityNotFoundException.class, () -> artistProxyService.merge(sourceArtist));
    }

    @Test
    void merge() {
        Artist sourceRepositoryArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Mockito.when(artistService.merge(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistProxyService.merge(sourceArtist);

        Artist expected = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckArtistServiceInputArgument() {
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location(""));

        artistProxyService.isFound(sourceArtist);

        Artist expectedArtist = new Artist("ARTIST_NAME", new Location(""));
        Mockito.verify(artistService).isFound(expectedArtist);
    }

    @Test
    void isFoundLocationWasNotChecked() {
        Artist sourceArtist = new Artist("", new Location("COUNTRY_NAME"));
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(false);

        artistProxyService.isFound(sourceArtist);

        Mockito.verifyNoInteractions(locationService);
    }

    @Test
    void isFoundCheckLocationServiceInputArgument() {
        Artist sourceArtist = new Artist("", new Location("COUNTRY_NAME"));
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(true);

        artistProxyService.isFound(sourceArtist);

        Location expectedLocation = new Location("COUNTRY_NAME");
        Mockito.verify(locationService).isFound(expectedLocation);
    }

    static Stream<Arguments> isFoundInputData() {
        return Stream.of(
                Arguments.of(true, true, true),
                Arguments.of(true, false, false),
                Arguments.of(false, true, false),
                Arguments.of(false, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(boolean isArtistFound, boolean isLocationFound, boolean expected) {
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(isArtistFound);
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(isLocationFound);

        boolean actual = artistProxyService.isFound(sourceArtist);

        assertEquals(expected, actual);
    }

}
