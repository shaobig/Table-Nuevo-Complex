package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
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

class AlbumServiceTest {

    private AlbumRepository albumRepository;

    private AlbumService albumService;

    @BeforeEach
    void init() {
        this.albumRepository = Mockito.mock(AlbumRepository.class);

        this.albumService = new AlbumService(albumRepository);
    }

    @Test
    void createCheckAlbum() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        albumService.create(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.verify(albumRepository).save(expectedAlbum);
    }

    @Test
    void create() {
        Album sourceRepositoryAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbum = new Album();
        Mockito.when(albumRepository.save(Mockito.any())).thenReturn(sourceRepositoryAlbum);

        Album actual = albumService.create(sourceAlbum);

        Album expected = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        assertEquals(expected, actual);
    }

    @Test
    void readCheckId() {
        Long sourceId = 1L;

        albumService.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumRepository).findById(expectedId);
    }

    static Stream<Arguments> readInputData() {
        return Stream.of(
                Arguments.of(Optional.empty(), Optional.empty()),
                Arguments.of(Optional.of(new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), Optional.of(new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "readInputData")
    void read(Optional<Album> sourceRepositoryAlbum, Optional<Album> expected) {
        Long sourceId = 1L;
        Mockito.when(albumRepository.findById(Mockito.anyLong())).thenReturn(sourceRepositoryAlbum);

        Optional<Album> actual = albumService.read(sourceId);

        assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        List<Album> sourceRepositoryAlbumList = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.when(albumRepository.readAll()).thenReturn(sourceRepositoryAlbumList);

        List<Album> actual = albumService.readAll();

        List<Album> expected = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

    @Test
    void updateCheckAlbum() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        albumService.update(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.verify(albumRepository).save(expectedAlbum);
    }

    @Test
    void update() {
        Album sourceRepositoryAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbum = new Album();
        Mockito.when(albumRepository.save(Mockito.any())).thenReturn(sourceRepositoryAlbum);

        Album actual = albumService.update(sourceAlbum);

        Album expected = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        assertEquals(expected, actual);
    }

}
