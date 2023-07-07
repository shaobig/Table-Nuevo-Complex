package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.location.LocationProxyService;
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

    private ArtistProxyService artistProxyService;

    @BeforeEach
    void init() {
        this.artistService = Mockito.mock(ArtistService.class);
        this.locationProxyService = Mockito.mock(LocationProxyService.class);

        this.artistProxyService = new ArtistProxyService(artistService, locationProxyService);
    }

    @Test
    void readEntityNotFound() {
        Optional<Artist> sourceArtist = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(artistService.read(Mockito.anyLong())).thenReturn(sourceArtist);

        assertThrows(EntityNotFoundException.class, () -> artistProxyService.read(sourceId));
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

}
