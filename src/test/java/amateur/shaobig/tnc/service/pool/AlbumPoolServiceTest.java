package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.repository.AlbumPoolRepository;
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

class AlbumPoolServiceTest {

    private AlbumPoolRepository albumPoolRepository;

    private AlbumPoolService albumPoolService;

    @BeforeEach
    void init() {
        this.albumPoolRepository = Mockito.mock(AlbumPoolRepository.class);

        this.albumPoolService = new AlbumPoolService(albumPoolRepository);
    }

    @Test
    void createCheckAlbumPool() {
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        albumPoolService.create(sourceAlbumPool);

        AlbumPool expectedAlbumPool = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumPoolRepository).save(expectedAlbumPool);
    }

    static Stream<Arguments> createInputData() {
        AlbumPool albumPoolEmpty = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool albumPoolEmptyExpected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        AlbumPool albumPoolWithName = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool albumPoolWithNameExpected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        return Stream.of(
                Arguments.of(albumPoolEmpty, albumPoolEmptyExpected),
                Arguments.of(albumPoolWithName, albumPoolWithNameExpected)
        );
    }

    @Test
    void create() {
        AlbumPool sourceAlbumPoolCreated = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        AlbumPool sourceAlbumPool = new AlbumPool(1L, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.when(albumPoolRepository.save(Mockito.any())).thenReturn(sourceAlbumPoolCreated);

        AlbumPool actual = albumPoolService.create(sourceAlbumPool);

        AlbumPool expected = new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

    static Stream<Arguments> readAllInputData() {
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListSource = List.of();
        List<ReadAllAlbumPoolDto> emptyAlbumPoolListExpected = List.of();

        List<ReadAllAlbumPoolDto> filledAlbumPoolListSource = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));
        List<ReadAllAlbumPoolDto> filledAlbumPoolListExpected = List.of(new ReadAllAlbumPoolDto(1L, 1L, "ARTIST_NAME",  "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)));

        return Stream.of(
                Arguments.of(emptyAlbumPoolListSource, emptyAlbumPoolListExpected),
                Arguments.of(filledAlbumPoolListSource, filledAlbumPoolListExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readAllInputData")
    void readAll(List<ReadAllAlbumPoolDto> sourceAlbums, List<ReadAllAlbumPoolDto> expected) {
        Mockito.when(albumPoolRepository.readAll()).thenReturn(sourceAlbums);

        List<ReadAllAlbumPoolDto> actual = albumPoolService.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void deleteCheckFindById() {
        Long sourceId = 1L;

        albumPoolService.delete(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumPoolRepository).findById(expectedId);
    }

    @Test
    void deleteCheckAlbumPool() {
        Long sourceId = 1L;

        albumPoolService.delete(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumPoolRepository).deleteById(expectedId);
    }

    static Stream<Arguments> deleteInputData() {
        Optional<AlbumPool> emptyAlbumPoolExpected = Optional.empty();
        Optional<AlbumPool> filledAlbumPoolExpected = Optional.of(new AlbumPool(1L, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));

        return Stream.of(
                Arguments.of(emptyAlbumPoolExpected),
                Arguments.of(filledAlbumPoolExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "deleteInputData")
    void delete(Optional<AlbumPool> expected) {
        Long sourceId = 1L;
        Mockito.when(albumPoolRepository.findById(Mockito.anyLong())).thenReturn(expected);

        Optional<AlbumPool> actual = albumPoolService.delete(sourceId);

        assertEquals(expected, actual);
    }

}
