package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.album.sorting.AlbumSongNumberListArranger;
import amateur.shaobig.tnc.service.artist.ArtistProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumProxyServiceTest {

    private AlbumService albumService;
    private ArtistProxyService artistProxyService;
    private AlbumSongNumberListArranger albumSongNumberListArranger;

    private AlbumProxyService albumProxyService;

    @BeforeEach
    void init() {
        this.albumService = Mockito.mock(AlbumService.class);
        this.artistProxyService = Mockito.mock(ArtistProxyService.class);
        this.albumSongNumberListArranger = Mockito.mock(AlbumSongNumberListArranger.class);

        this.albumProxyService = new AlbumProxyService(albumService, artistProxyService, albumSongNumberListArranger);
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
