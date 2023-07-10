package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.artist.ArtistProxyService;
import amateur.shaobig.tnc.sorting.ComparatorListArranger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlbumProxyServiceTest {

    private AlbumService albumService;
    private ArtistProxyService artistProxyService;
    private ComparatorListArranger<Song> songComparatorListArranger;

    private AlbumProxyService albumProxyService;

    @BeforeEach
    void init() {
        this.albumService = Mockito.mock(AlbumService.class);
        this.artistProxyService = Mockito.mock(ArtistProxyService.class);
        this.songComparatorListArranger = Mockito.mock(ComparatorListArranger.class);

        this.albumProxyService = new AlbumProxyService(albumService, artistProxyService, songComparatorListArranger);
    }

    @Test
    void createCheckArtist() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY"), List.of()), List.of(), List.of());

        albumProxyService.create(sourceAlbum);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY"), List.of());
        Mockito.verify(artistProxyService).create(expectedArtist);
    }

    @Test
    void createCheckAlbum() {
        Artist sourceCreatedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.when(artistProxyService.create(Mockito.any())).thenReturn(sourceCreatedArtist);

        albumProxyService.create(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of()), List.of(), List.of());
        Mockito.verify(albumService).create(expectedAlbum);
    }

    @Test
    void create() {
        Album sourceRepositoryAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbum = new Album();
        Mockito.when(albumService.create(Mockito.any())).thenReturn(sourceRepositoryAlbum);

        Album actual = albumProxyService.create(sourceAlbum);

        Album expected = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        assertEquals(expected, actual);
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

    @Test
    void readCheckSongs() {
        List<Song> sourceAlbumSongs = List.of(new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Optional<Album> sourceAlbum = Optional.of(new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), sourceAlbumSongs));
        Long sourceId = 1L;
        Mockito.when(albumService.read(Mockito.any())).thenReturn(sourceAlbum);

        albumProxyService.read(sourceId);

        List<Song> expectedAlbumSongs = List.of(new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Mockito.verify(songComparatorListArranger).arrange(expectedAlbumSongs);
    }

    @Test
    void read() {
        List<Song> sourceSortedSongs = List.of(new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Optional<Album> sourceAlbum = Optional.of(new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of(new Song(), new Song())));
        Long sourceId = 1L;
        Mockito.when(albumService.read(Mockito.any())).thenReturn(sourceAlbum);
        Mockito.when(songComparatorListArranger.arrange(Mockito.anyList())).thenReturn(sourceSortedSongs);

        Album actual = albumProxyService.read(sourceId);

        Album expected = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of(new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 0, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));
        assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        List<Album> sourceAlbums = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.when(albumService.readAll()).thenReturn(sourceAlbums);

        List<Album> actual = albumProxyService.readAll();

        List<Album> expected = List.of(new Album(1L, 0, "ALBUM_NAME_1", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()), new Album(1L, 0, "ALBUM_NAME_2", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        assertEquals(expected, actual);
    }

    @Test
    void updateCheckAlbum() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        albumProxyService.update(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.verify(albumService).update(expectedAlbum);
    }

    @Test
    void update() {
        Album sourceRepositoryAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Album sourceAlbum = new Album();
        Mockito.when(albumService.update(Mockito.any())).thenReturn(sourceRepositoryAlbum);

        Album actual = albumProxyService.update(sourceAlbum);

        Album expected = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        assertEquals(expected, actual);
    }

}
