package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumProxyServiceTest {

    private AlbumService albumService;

    private AlbumProxyService albumProxyService;

    @BeforeEach
    void init() {
        this.albumService = Mockito.mock(AlbumService.class);

        this.albumProxyService = new AlbumProxyService(albumService);
    }

    @Test
    void readNullId() {
        Mockito.when(albumService.read(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> albumProxyService.read(null));
    }

    @Test
    void readCheckId() {
        Optional<Album> sourceAlbum = Optional.of(new Album());
        Long sourceId = 1L;
        Mockito.when(albumService.read(Mockito.anyLong())).thenReturn(sourceAlbum);

        albumProxyService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumService).read(expectedId);
    }

    @Test
    void readAlbumNotFound() {
        Optional<Album> sourceAlbum = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(albumService.read(Mockito.anyLong())).thenReturn(sourceAlbum);

        assertThrows(EntityNotFoundException.class, () -> albumProxyService.read(sourceId));
    }

}
