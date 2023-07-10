package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.repository.ArtistRepository;
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

class ArtistServiceTest {

    private ArtistRepository artistRepository;

    private ArtistService artistService;

    @BeforeEach
    void init() {
        this.artistRepository = Mockito.mock(ArtistRepository.class);

        this.artistService = new ArtistService(artistRepository);
    }

    @Test
    void create() {
        Artist sourceRepositoryArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(), List.of());
        Mockito.when(artistRepository.save(Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistService.create(sourceArtist);

        Artist expected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of());
        assertEquals(expected, actual);
    }

    @Test
    void readCheckId() {
        Long sourceId = 1L;

        artistService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(artistRepository).findById(expectedId);
    }

    static Stream<Arguments> readInputData() {
        return Stream.of(
                Arguments.of(Optional.empty(), Optional.empty()),
                Arguments.of(Optional.of(new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of())), Optional.of(new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of())))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readInputData")
    void read(Optional<Artist> sourceRepositoryArtist, Optional<Artist> expected) {
        Long sourceId = 1L;
        Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(sourceRepositoryArtist);

        Optional<Artist> actual = artistService.read(sourceId);

        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckName() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.isFound(sourceArtist);

        String expectedName = "ARTIST_NAME";
        Mockito.verify(artistRepository).existsByNameAndStatusAndLocation(Mockito.eq(expectedName), Mockito.any(), Mockito.any());
    }

    @Test
    void isFoundCheckStatus() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.isFound(sourceArtist);

        ArtistStatus expectedStatus = ArtistStatus.ACTIVE;
        Mockito.verify(artistRepository).existsByNameAndStatusAndLocation(Mockito.any(), Mockito.eq(expectedStatus), Mockito.any());
    }

    @Test
    void isFoundCheckLocation() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.isFound(sourceArtist);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(artistRepository).existsByNameAndStatusAndLocation(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocation));
    }

    static Stream<Arguments> isFoundInputData() {
        return Stream.of(
                Arguments.of(true, true),
                Arguments.of(false, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(boolean isFound, boolean expected) {
        Artist sourceArtist = new Artist();
        Mockito.when(artistRepository.existsByNameAndStatusAndLocation(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(isFound);

        boolean actual = artistService.isFound(sourceArtist);

        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckName() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.merge(sourceArtist);

        String expectedName = "ARTIST_NAME";
        Mockito.verify(artistRepository).findByNameAndStatusAndLocation(Mockito.eq(expectedName), Mockito.any(), Mockito.any());
    }

    @Test
    void mergeCheckStatus() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.merge(sourceArtist);

        ArtistStatus expectedStatus = ArtistStatus.ACTIVE;
        Mockito.verify(artistRepository).findByNameAndStatusAndLocation(Mockito.any(), Mockito.eq(expectedStatus), Mockito.any());
    }

    @Test
    void mergeCheckLocation() {
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        artistService.merge(sourceArtist);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(artistRepository).findByNameAndStatusAndLocation(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocation));
    }

    @Test
    void merge() {
        Artist sourceRepositoryArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Artist sourceArtist = new Artist();
        Mockito.when(artistRepository.findByNameAndStatusAndLocation(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(sourceRepositoryArtist);

        Artist actual = artistService.merge(sourceArtist);

        Artist expected = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        assertEquals(expected, actual);
    }

}
