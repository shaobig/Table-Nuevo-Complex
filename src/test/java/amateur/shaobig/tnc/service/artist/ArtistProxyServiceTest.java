package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
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

        this.artistProxyService = new ArtistProxyService(artistService, albumTypeYearListArranger);
    }

    @Test
    void readEntityNotFound() {
        Optional<Artist> sourceArtist = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        assertThrows(EntityNotFoundException.class, () -> artistProxyService.read(sourceId));
    }

    @Test
    void readCheckArrangedAlbums() {
        List<Album> sourceAlbums = List.of(new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Optional<Artist> sourceArtist = Optional.of(new Artist(1L, "", ArtistStatus.ACTIVE, new Location(), sourceAlbums));
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        artistProxyService.read(sourceId);

        List<Album> expectedAlbums = List.of(new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumTypeYearListArranger).arrange(expectedAlbums);
    }

    static Stream<Arguments> readInput() {
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

        List<Artist> sourceFilledList = List.of(new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of()));
        List<Artist> expectedFilledList = List.of(new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of()));

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
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());

        artistProxyService.merge(sourceArtist);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Mockito.verify(artistService).merge(expectedArtist);
    }

    @Test
    void mergeArtistNotFound() {
        Artist sourceArtist = new Artist();
        Mockito.when(artistService.merge(Mockito.any())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> artistProxyService.merge(sourceArtist));
    }

    @Test
    void merge() {
        Artist sourceRepositoryArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Mockito.when(artistService.merge(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistProxyService.merge(sourceArtist);

        Artist expected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckArtist() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());

        artistProxyService.isFound(sourceArtist);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Mockito.verify(artistService).isFound(expectedArtist);
    }

    @Test
    void isFoundArtistFoundNoInteractionsWithLocationService() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(), List.of());;
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(true);

        artistProxyService.isFound(sourceArtist);

        Mockito.verifyNoInteractions(locationService);
    }

    static Stream<Arguments> isFoundInputData() {
        return Stream.of(
                Arguments.of(false, false, false),
                Arguments.of(false, true, false),
                Arguments.of(true, true, true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(boolean isArtistFound, boolean isLocationFound, boolean expected) {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(), List.of());;
        Mockito.when(artistService.isFound(Mockito.any())).thenReturn(isArtistFound);
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(isLocationFound);

        boolean actual = artistProxyService.isFound(sourceArtist);

        assertEquals(expected, actual);
    }

}
