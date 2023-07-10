package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.DeleteAlbumDto;
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

class DeleteAlbumDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private AlbumGenreDtoTransformer albumGenreDtoTransformer;
    private SongDtoTransformer songDtoTransformer;

    private DeleteAlbumDtoTransformer deleteAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);
        this.albumGenreDtoTransformer = Mockito.mock(AlbumGenreDtoTransformer.class);
        this.songDtoTransformer = Mockito.mock(SongDtoTransformer.class);

        this.deleteAlbumDtoTransformer = new DeleteAlbumDtoTransformer(albumMetadataDtoTransformer, albumGenreDtoTransformer, songDtoTransformer);
    }

    @Test
    void transformCheckAlbumMetadataDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Artist(), List.of(), List.of());

        deleteAlbumDtoTransformer.transform(sourceAlbum);

        AlbumMetadata expectedAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumMetadataDtoTransformer).transform(expectedAlbumMetadata);
    }

    @Test
    void transformCheckGenreDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1")), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2"))), List.of());

        deleteAlbumDtoTransformer.transform(sourceAlbum);
        ArgumentCaptor<AlbumGenre> genreArgumentCaptor = ArgumentCaptor.forClass(AlbumGenre.class);
        Mockito.verify(albumGenreDtoTransformer, Mockito.atLeastOnce()).transform(genreArgumentCaptor.capture());
        List<AlbumGenre> actual = genreArgumentCaptor.getAllValues();

        List<AlbumGenre> expected = List.of(new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_1")), new AlbumGenre(1L, true, new Genre(1L, "GENRE_NAME_2")));
        assertEquals(expected, actual);
    }

    @Test
    void transformCheckSongDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));

        deleteAlbumDtoTransformer.transform(sourceAlbum);
        ArgumentCaptor<Song> songArgumentCaptor = ArgumentCaptor.forClass(Song.class);
        Mockito.verify(songDtoTransformer, Mockito.atLeastOnce()).transform(songArgumentCaptor.capture());
        List<Song> actual = songArgumentCaptor.getAllValues();

        List<Song> expected = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        assertEquals(expected, actual);
    }

    @Test
    void transform() {
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        AlbumGenreDto sourceFirstAlbumGenreDto = new AlbumGenreDto(1L, "GENRE_NAME_1", true);
        AlbumGenreDto sourceSecondAlbumGenreDto = new AlbumGenreDto(1L, "GENRE_NAME_2", true);
        SongDto sourceFirstSongDto = new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1));
        SongDto sourceSecondSongDto = new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1));
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new AlbumGenre(), new AlbumGenre()), List.of(new Song(), new Song()));
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);
        Mockito.when(albumGenreDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstAlbumGenreDto).thenReturn(sourceSecondAlbumGenreDto);
        Mockito.when(songDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstSongDto).thenReturn(sourceSecondSongDto);

        DeleteAlbumDto actual = deleteAlbumDtoTransformer.transform(sourceAlbum);

        DeleteAlbumDto expected = new DeleteAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new AlbumGenreDto(1L, "GENRE_NAME_1", true), new AlbumGenreDto(1L, "GENRE_NAME_2", true)), List.of(new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1)), new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1))));
        assertEquals(expected, actual);
    }

}
