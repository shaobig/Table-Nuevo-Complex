package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.album.AlbumProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumPoolProxyServiceTest {

    private AlbumPoolService albumPoolService;
    private AlbumProxyService albumProxyService;

    private AlbumPoolProxyService albumPoolProxyService;

    @BeforeEach
    void init() {
        this.albumProxyService = Mockito.mock(AlbumProxyService.class);
        this.albumPoolService = Mockito.mock(AlbumPoolService.class);

        this.albumPoolProxyService = new AlbumPoolProxyService(albumPoolService, albumProxyService);
    }

    static Stream<Arguments> readAllInputData() {
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListSource = List.of();
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListExpected = List.of();

        List<ReadAllAlbumPoolDto> filledAlbumPoolListSource = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME","COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));
        List<ReadAllAlbumPoolDto> filledAlbumPoolListExpected = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));

        return Stream.of(
                Arguments.of(emptyAlbumPoolListSource, emptyAlbumPoolListExpected),
                Arguments.of(filledAlbumPoolListSource, filledAlbumPoolListExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInputData")
    void readAll(List<ReadAllAlbumPoolDto> sourceAlbums, List<ReadAllAlbumPoolDto> expected) {
        Mockito.when(albumPoolService.readAll()).thenReturn(sourceAlbums);

        List<ReadAllAlbumPoolDto> actual = albumPoolProxyService.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void deleteCheckId() {
        Optional<AlbumPool> sourceAlbumPool = Optional.of(new AlbumPool());
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        albumPoolProxyService.delete(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumPoolService).delete(expectedId);
    }

    @Test
    void deleteAlbumPoolNotFound() {
        Optional<AlbumPool> sourceAlbumPool = Optional.empty();
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        assertThrows(EntityNotFoundException.class, () -> albumPoolProxyService.delete(sourceId));
    }

    @Test
    void delete() {
        Optional<AlbumPool> sourceAlbumPool = Optional.of(new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Long sourceId = 1L;
        Mockito.when(albumPoolService.delete(Mockito.anyLong())).thenReturn(sourceAlbumPool);

        AlbumPool actual = albumPoolProxyService.delete(sourceId);

        AlbumPool expected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

}
