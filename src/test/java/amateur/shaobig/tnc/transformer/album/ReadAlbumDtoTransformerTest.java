package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.entity.enums.SongType;
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

class ReadAlbumDtoTransformerTest {

    private SongDtoTransformer songDtoTransformer;
    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;

    private ReadAlbumDtoTransformer readAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.songDtoTransformer = Mockito.mock(SongDtoTransformer.class);
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);

        this.readAlbumDtoTransformer = new ReadAlbumDtoTransformer(songDtoTransformer, albumMetadataDtoTransformer);
    }

    @Test
    void transformCheckSongDtoTransformer() {
        List<Song> sourceSongs = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), sourceSongs);

        readAlbumDtoTransformer.transform(sourceAlbum);
        ArgumentCaptor<Song> songArgumentCaptor = ArgumentCaptor.forClass(Song.class);
        Mockito.verify(songDtoTransformer, atLeastOnce()).transform(songArgumentCaptor.capture());
        List<Song> actual = songArgumentCaptor.getAllValues();

        List<Song> expected = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_2", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        assertEquals(expected, actual);
    }

    @Test
    void transformCheckAlbumMetadataDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Artist(), List.of(), List.of());

        readAlbumDtoTransformer.transform(sourceAlbum);

        AlbumMetadata expectedAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumMetadataDtoTransformer).transform(expectedAlbumMetadata);
    }

    @Test
    void transform() {
        SongDto sourceFirstSongDto = new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1));
        SongDto sourceSecondSongDto = new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1));
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(), List.of()), List.of(), List.of(new Song(), new Song()));
        Mockito.when(songDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstSongDto).thenReturn(sourceSecondSongDto);
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);

        ReadAlbumDto actual = readAlbumDtoTransformer.transform(sourceAlbum);

        ReadAlbumDto expected = new ReadAlbumDto(1L, "ARTIST_NAME", 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), List.of(new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1)), new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1))));
        assertEquals(expected, actual);
    }

}
