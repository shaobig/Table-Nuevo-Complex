package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArtistServiceTest {

    private ArtistRepository artistRepository;

    private ArtistService artistService;

    @BeforeEach
    void init() {
        this.artistRepository = Mockito.mock(ArtistRepository.class);

        this.artistService = new ArtistService(artistRepository);
    }

    @Test
    void createArtistIsNull() {
        Mockito.when(artistRepository.save(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> artistService.create(null));
    }

    @Test
    void create() {
        Artist sourceRepositoryArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"), ArtistStatus.ACTIVE);
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"), ArtistStatus.ACTIVE);
        Mockito.when(artistRepository.save(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistService.create(sourceArtist);

        Artist expected = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"), ArtistStatus.ACTIVE);
        assertEquals(expected, actual);
    }

    @Test
    void readNullId() {
        Mockito.when(artistRepository.findById(Mockito.any())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> artistService.read(null));
    }

    @Test
    void readCheckId() {
        Long sourceId = 1L;

        artistService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(artistRepository).findById(expectedId);
    }

    @Test
    void readArtistNotFound() {
        Long sourceId = 1L;
        Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Artist> actual = artistService.read(sourceId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void readFoundArtist() {
        Optional<Artist> sourceArtist = Optional.of(new Artist());
        Long sourceId = 1L;
        Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(sourceArtist);

        Optional<Artist> actual = artistService.read(sourceId);

        assertTrue(actual.isPresent());
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
        Mockito.when(artistRepository.findAll()).thenReturn(sourceArtists);

        List<Artist> actual = artistService.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckId() {
        Artist sourceArtist = new Artist();
        Long sourceId = 1L;
        sourceArtist.setId(sourceId);

        artistService.merge(sourceArtist);

        Long expectedId = 1L;
        Mockito.verify(artistRepository).getReferenceById(expectedId);
    }

    @Test
    void mergeArtistWithoutPrimaryKey() {
        Artist sourceArtist = new Artist();
        Mockito.when(artistRepository.getReferenceById(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> artistService.merge(sourceArtist));
    }

    @Test
    void mergeArtistNotFound() {
        Artist sourceArtist = new Artist();
        Long sourceId = 1L;
        sourceArtist.setId(sourceId);
        Mockito.when(artistRepository.getReferenceById(Mockito.anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> artistService.merge(sourceArtist));
    }

    @Test
    void merge() {
        Artist sourceRepositoryArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        Mockito.when(artistRepository.getReferenceById(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistService.merge(sourceArtist);

        Artist expected = new Artist("ARTIST_NAME", new Location("COUNTRY_NAME"));
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckName() {
        String sourceName = "ARTIST_NAME";
        Artist sourceArtist = new Artist(sourceName, Mockito.mock(Location.class))
                ;
        artistService.isFound(sourceArtist);

        String expectedName = "ARTIST_NAME";
        Mockito.verify(artistRepository).existsByNameAndStatus(Mockito.eq(expectedName), Mockito.any());
    }

    @Test
    void isFoundCheckStatus() {
        Artist sourceArtist = new Artist();
        ArtistStatus sourceStatus = ArtistStatus.ACTIVE;
        sourceArtist.setStatus(sourceStatus);
                ;
        artistService.isFound(sourceArtist);

        ArtistStatus expectedStatus = ArtistStatus.ACTIVE;
        Mockito.verify(artistRepository).existsByNameAndStatus(Mockito.any(), Mockito.eq(expectedStatus));
    }

    static Stream<Arguments> isFoundInputData() {
        Artist artistWithEmptyName = new Artist("", Mockito.mock(Location.class));
        Artist artistWithName = new Artist("ARTIST_NAME", Mockito.mock(Location.class));
        Artist artistWithNameAndStatus = new Artist("ARTIST_NAME", Mockito.mock(Location.class), ArtistStatus.ACTIVE);

        return Stream.of(
                Arguments.of(artistWithEmptyName, false),
                Arguments.of(artistWithName, true),
                Arguments.of(artistWithNameAndStatus, true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(Artist sourceArtist, boolean expected) {
        Mockito.when(artistRepository.existsByNameAndStatus(Mockito.any(), Mockito.any())).thenReturn(expected);

        boolean actual = artistService.isFound(sourceArtist);

        assertEquals(expected, actual);
    }

}
