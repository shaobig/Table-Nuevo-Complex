package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.genre.GenreDtoTransformer;
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

class UpdateAlbumDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private GenreDtoTransformer genreDtoTransformer;
    private SongDtoTransformer songDtoTransformer;

    private UpdateAlbumDtoTransformer updateAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);
        this.genreDtoTransformer = Mockito.mock(GenreDtoTransformer.class);
        this.songDtoTransformer = Mockito.mock(SongDtoTransformer.class);

        this.updateAlbumDtoTransformer = new UpdateAlbumDtoTransformer(albumMetadataDtoTransformer, genreDtoTransformer, songDtoTransformer);
    }

    @Test
    void transformCheckAlbumMetadata() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Artist(), List.of(), List.of());

        updateAlbumDtoTransformer.transform(sourceAlbum);

        AlbumMetadata expectedAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumMetadataDtoTransformer).transform(expectedAlbumMetadata);
    }

    @Test
    void transformCheckGenres() {
        List<Genre> sourceGenres = List.of(new Genre(1L, "GENRE_NAME_1", false, new Album()), new Genre(1L, "GENRE_NAME_2", false, new Album()));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), sourceGenres, List.of());

        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        updateAlbumDtoTransformer.transform(sourceAlbum);
        Mockito.verify(genreDtoTransformer, atLeastOnce()).transform(genreArgumentCaptor.capture());
        List<Genre> actual = genreArgumentCaptor.getAllValues();

        List<Genre> expected = List.of(new Genre(1L, "GENRE_NAME_1", false, new Album()), new Genre(1L, "GENRE_NAME_2", false, new Album()));
        assertEquals(expected, actual);
    }

    @Test
    void transformCheckSongs() {
        List<Song> sourceSongs = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), sourceSongs);

        ArgumentCaptor<Song> songArgumentCaptor = ArgumentCaptor.forClass(Song.class);
        updateAlbumDtoTransformer.transform(sourceAlbum);
        Mockito.verify(songDtoTransformer, atLeastOnce()).transform(songArgumentCaptor.capture());
        List<Song> actual = songArgumentCaptor.getAllValues();

        List<Song> expected = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        assertEquals(expected, actual);
    }

    @Test
    void transform() {
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        GenreDto sourceFirstGenre = new GenreDto(1L, "GENRE_NAME_1", false);
        GenreDto sourceSecondGenre = new GenreDto(1L, "GENRE_NAME_2", false);
        SongDto sourceFirstSong = new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1));
        SongDto sourceSecondSong = new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1));
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new Genre(), new Genre()), List.of(new Song(), new Song()));
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);
        Mockito.when(genreDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstGenre).thenReturn(sourceSecondGenre);
        Mockito.when(songDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstSong).thenReturn(sourceSecondSong);

        UpdateAlbumDto actual = updateAlbumDtoTransformer.transform(sourceAlbum);

        UpdateAlbumDto expected = new UpdateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new GenreDto(1L, "GENRE_NAME_1", false), new GenreDto(1L, "GENRE_NAME_2", false)), List.of(new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1)), new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1))));
        assertEquals(expected, actual);
    }

}
