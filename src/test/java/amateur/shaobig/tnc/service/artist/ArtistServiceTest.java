package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
