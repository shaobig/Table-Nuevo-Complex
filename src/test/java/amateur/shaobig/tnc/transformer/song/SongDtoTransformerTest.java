package amateur.shaobig.tnc.transformer.song;

import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.song.metadata.SongMetadataDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongDtoTransformerTest {

    private SongMetadataDtoTransformer songMetadataDtoTransformer;

    private SongDtoTransformer songDtoTransformer;

    @BeforeEach
    void init() {
        this.songMetadataDtoTransformer = Mockito.mock(SongMetadataDtoTransformer.class);

        this.songDtoTransformer = new SongDtoTransformer(songMetadataDtoTransformer);
    }

    @Test
    void transformCheckSongMetadata() {
        SongMetadata sourceSongMetadata = new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Song sourceSong = new Song(1L, 0,"", sourceSongMetadata, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));

        songDtoTransformer.transform(sourceSong);

        SongMetadata expectedSongMetadata = new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        Mockito.verify(songMetadataDtoTransformer).transform(expectedSongMetadata);
    }

    @Test
    void transform() {
        Song sourceSong = new Song(1L, 0,"SONG_NAME", new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        SongMetadataDto sourceSongMetadataDto = new SongMetadataDto(SongType.DEFAULT, 1);
        Mockito.when(songMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceSongMetadataDto);

        SongDto actual = songDtoTransformer.transform(sourceSong);

        SongDto expected = new SongDto(1L, 0, "SONG_NAME", new SongMetadataDto(SongType.DEFAULT, 1));
        assertEquals(expected, actual);
    }

}
