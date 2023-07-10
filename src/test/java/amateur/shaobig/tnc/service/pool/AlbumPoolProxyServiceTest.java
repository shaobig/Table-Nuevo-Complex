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

    @Test
    void createCheckAlbum() {
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        albumPoolProxyService.create(sourceAlbumPool);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.verify(albumProxyService).create(expectedAlbum);
    }

    @Test
    void createCheckAlbumPool() {
        Album sourceRepositoryAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        AlbumPool sourceAlbumPool = new AlbumPool();
        Mockito.when(albumProxyService.create(Mockito.any())).thenReturn(sourceRepositoryAlbum);

        albumPoolProxyService.create(sourceAlbumPool);

        AlbumPool expectedAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumPoolService).create(expectedAlbumPool);
    }

    @Test
    void create() {
        AlbumPool sourceRepositoryAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool sourceAlbumPool = new AlbumPool();
        Mockito.when(albumPoolService.create(Mockito.any())).thenReturn(sourceRepositoryAlbumPool);

        AlbumPool actual = albumPoolProxyService.create(sourceAlbumPool);

        AlbumPool expected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

    static Stream<Arguments> readAllInputData() {
        List<ReadAllAlbumPoolDto> sourceEmptyAlbumPoolList = List.of();
        List<ReadAllAlbumPoolDto> sourceEmptyAlbumPoolListExpected = List.of();

        List<ReadAllAlbumPoolDto> sourceFilledAlbumPoolList = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));
        List<ReadAllAlbumPoolDto> sourceFilledAlbumPoolListExpected = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));

        return Stream.of(
                Arguments.of(sourceEmptyAlbumPoolList, sourceEmptyAlbumPoolListExpected),
                Arguments.of(sourceFilledAlbumPoolList, sourceFilledAlbumPoolListExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInputData")
    void readAll(List<ReadAllAlbumPoolDto> sourceRepositoryAlbums, List<ReadAllAlbumPoolDto> expected) {
        Mockito.when(albumPoolService.readAll()).thenReturn(sourceRepositoryAlbums);

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
    void deleteAlbumPoolIsNotFound() {
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
