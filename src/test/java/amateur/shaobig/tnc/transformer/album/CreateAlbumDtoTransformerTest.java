package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.CreateAlbumDto;
import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.genre.AlbumGenreDtoTransformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;

class CreateAlbumDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private AlbumGenreDtoTransformer albumGenreDtoTransformer;
    private SongDtoTransformer songDtoTransformer;

    private CreateAlbumDtoTransformer createAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);
        this.albumGenreDtoTransformer = Mockito.mock(AlbumGenreDtoTransformer.class);
        this.songDtoTransformer = Mockito.mock(SongDtoTransformer.class);

        this.createAlbumDtoTransformer = new CreateAlbumDtoTransformer(albumMetadataDtoTransformer, albumGenreDtoTransformer, songDtoTransformer);
    }

    @Test
    void transformCheckAlbumMetadata() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Artist(), List.of(), List.of());

        createAlbumDtoTransformer.transform(sourceAlbum);

        AlbumMetadata expectedAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumMetadataDtoTransformer).transform(expectedAlbumMetadata);
    }

    @Test
    void transformCheckGenres() {
        List<AlbumGenre> sourceAlbumGenres = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), sourceAlbumGenres, List.of());

        ArgumentCaptor<AlbumGenre> genreArgumentCaptor = ArgumentCaptor.forClass(AlbumGenre.class);
        createAlbumDtoTransformer.transform(sourceAlbum);
        Mockito.verify(albumGenreDtoTransformer, atLeastOnce()).transform(genreArgumentCaptor.capture());
        List<AlbumGenre> actual = genreArgumentCaptor.getAllValues();

        List<AlbumGenre> expected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1"), new Album()), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"), new Album()));
        assertEquals(expected, actual);
    }

    @Test
    void transformCheckSongs() {
        List<Song> sourceSongs = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), sourceSongs);

        ArgumentCaptor<Song> songArgumentCaptor = ArgumentCaptor.forClass(Song.class);
        createAlbumDtoTransformer.transform(sourceAlbum);
        Mockito.verify(songDtoTransformer, atLeastOnce()).transform(songArgumentCaptor.capture());
        List<Song> actual = songArgumentCaptor.getAllValues();

        List<Song> expected = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        assertEquals(expected, actual);
    }

    @Test
    void transform() {
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        AlbumGenreDto sourceFirstGenre = new AlbumGenreDto(1L, "GENRE_NAME_1", false);
        AlbumGenreDto sourceSecondGenre = new AlbumGenreDto(1L, "GENRE_NAME_2", false);
        SongDto sourceFirstSong = new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1));
        SongDto sourceSecondSong = new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1));
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new AlbumGenre(), new AlbumGenre()), List.of(new Song(), new Song()));
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);
        Mockito.when(albumGenreDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstGenre).thenReturn(sourceSecondGenre);
        Mockito.when(songDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstSong).thenReturn(sourceSecondSong);

        CreateAlbumDto actual = createAlbumDtoTransformer.transform(sourceAlbum);

        CreateAlbumDto expected = new CreateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new AlbumGenreDto(1L, "GENRE_NAME_1", false), new AlbumGenreDto(1L, "GENRE_NAME_2", false)), List.of(new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1)), new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1))));
        assertEquals(expected, actual);
    }

}
