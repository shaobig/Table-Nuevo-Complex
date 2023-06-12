package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.repository.AlbumRepository;
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

class AlbumServiceTest {

    private AlbumRepository albumRepository;

    private AlbumService albumService;

    @BeforeEach
    void init() {
        this.albumRepository = Mockito.mock(AlbumRepository.class);

        this.albumService = new AlbumService(albumRepository);
    }

    @Test
    void readNullId() {
        Mockito.when(albumRepository.findById(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> albumService.read(null));
    }

    @Test
    void readCheckId() {
        Long sourceId = 1L;

        albumService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumRepository).findById(expectedId);
    }

    @Test
    void readAlbumNotFound() {
        Long sourceId = 1L;
        Mockito.when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Album> actual = albumService.read(sourceId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void readFoundAlbum() {
        Optional<Album> sourceAlbum = Optional.of(new Album());
        Long sourceId = 1L;
        Mockito.when(albumRepository.findById(Mockito.anyLong())).thenReturn(sourceAlbum);

        Optional<Album> actual = albumService.read(sourceId);

        assertTrue(actual.isPresent());
    }

}
