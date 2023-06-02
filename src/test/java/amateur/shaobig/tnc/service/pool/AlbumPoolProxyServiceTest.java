package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.service.artist.ArtistService;
import amateur.shaobig.tnc.service.location.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumPoolProxyServiceTest {

    private AlbumPoolService albumPoolService;
    private LocationService locationService;
    private ArtistService artistService;

    private AlbumPoolProxyService albumPoolProxyService;

    @BeforeEach
    void init() {
        this.albumPoolService = Mockito.mock(AlbumPoolService.class);
        this.locationService = Mockito.mock(LocationService.class);
        this.artistService = Mockito.mock(ArtistService.class);

        this.albumPoolProxyService = new AlbumPoolProxyService(albumPoolService, locationService, artistService);
    }

    @Test
    void createLocationIsFound() {
        Artist sourceArtist = new Artist("", new Location("COUNTRY_NAME"));
        Album sourceAlbum = new Album();
        sourceAlbum.setArtist(sourceArtist);
        AlbumPool sourceAlbumPool = new AlbumPool(sourceAlbum);

        albumPoolProxyService.create(sourceAlbumPool);

        Location expectedLocation = new Location("COUNTRY_NAME");
        Mockito.verify(locationService).isFound(expectedLocation);
    }

    @Test
    void createArtistIdNotNull() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", new Location("COUNTRY_NAME"));
        Album sourceAlbum = new Album();
        sourceAlbum.setArtist(sourceArtist);
        AlbumPool sourceAlbumPool = new AlbumPool(sourceAlbum);

        albumPoolProxyService.create(sourceAlbumPool);

        Artist expectedArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Mockito.verify(artistService).merge(expectedArtist);
    }

    static Stream<Arguments> createInputData() {
        AlbumPool albumPoolWithNewLocation = new AlbumPool(new Album("ALBUM_NAME", 1970, new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"))));
        AlbumPool albumPoolWithNewLocationExpected = new AlbumPool(1L, new Album(1L, "ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location(1L,"COUNTRY_NAME"))));

        AlbumPool albumPoolWithExistingLocation = new AlbumPool(new Album("ALBUM_NAME", 1970, new Artist("ARTIST_NAME", new Location(1L, "COUNTRY_NAME"))));
        AlbumPool albumPoolWithExistingLocationExpected = new AlbumPool(1L, new Album(1L, "ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location(1L,"COUNTRY_NAME"))));

        AlbumPool albumPoolWithExistingArtist = new AlbumPool(new Album("ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location("COUNTRY_NAME"))));
        AlbumPool albumPoolWithExistingArtistExpected = new AlbumPool(1L, new Album(1L, "ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location(1L,"COUNTRY_NAME"))));

        AlbumPool albumPoolWithExistingArtistAndLocation = new AlbumPool(new Album("ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location(1L,"COUNTRY_NAME"))));
        AlbumPool albumPoolWithExistingArtistAndLocationExpected = new AlbumPool(1L, new Album(1L, "ALBUM_NAME", 1970, new Artist(1L, "ARTIST_NAME", new Location(1L,"COUNTRY_NAME"))));

        return Stream.of(
                Arguments.of(albumPoolWithNewLocation, false, albumPoolWithNewLocationExpected),
                Arguments.of(albumPoolWithExistingLocation, true, albumPoolWithExistingLocationExpected),
                Arguments.of(albumPoolWithExistingArtist, false, albumPoolWithExistingArtistExpected),
                Arguments.of(albumPoolWithExistingArtistAndLocation, true, albumPoolWithExistingArtistAndLocationExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "createInputData")
    void create(AlbumPool sourceAlbumPool, boolean isLocationFound, AlbumPool expected) {
        Location sourceMergedLocation = new Location(1L, "COUNTRY_NAME");
        Artist sourceMergedArtist = new Artist(1L, "ARTIST_NAME", new Location(1L, "COUNTRY_NAME"));
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(isLocationFound);
        Mockito.when(albumPoolService.create(Mockito.any())).thenReturn(expected);
        Mockito.when(locationService.merge(Mockito.any())).thenReturn(sourceMergedLocation);
        Mockito.when(artistService.merge(Mockito.any())).thenReturn(sourceMergedArtist);

        AlbumPool actual = albumPoolProxyService.create(sourceAlbumPool);

        assertEquals(expected, actual);
    }

}