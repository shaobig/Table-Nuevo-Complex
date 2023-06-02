package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
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

public class AlbumProxyServiceTest {

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

    static Stream<Arguments> readAllInputData() {
        List<ReadAllAlbumDto> emptyAlbumListSource = List.of();
        List<ReadAllAlbumDto> emptyAlbumListExpected = List.of();

        List<ReadAllAlbumDto> filledAlbumListSource = List.of(new ReadAllAlbumDto(1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0));
        List<ReadAllAlbumDto> filledAlbumListExpected = List.of(new ReadAllAlbumDto(1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0));

        return Stream.of(
                Arguments.of(emptyAlbumListSource, emptyAlbumListExpected),
                Arguments.of(filledAlbumListSource, filledAlbumListExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInputData")
    void readAll(List<ReadAllAlbumDto> sourceAlbums, List<ReadAllAlbumDto> expected) {
        Mockito.when(albumService.readAll()).thenReturn(sourceAlbums);

        List<ReadAllAlbumDto> actual = albumService.readAll();

        assertEquals(expected, actual);
    }

}