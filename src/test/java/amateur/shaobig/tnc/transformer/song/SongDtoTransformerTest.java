package amateur.shaobig.tnc.transformer.song;

import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.song.metadata.SongMetadataDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        SongMetadata sourceSongMetadata = new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 0)));
        Song sourceSong = new Song(1L, 0,"", sourceSongMetadata, new Album("", 0));

        songDtoTransformer.transform(sourceSong);

        SongMetadata expectedSongMetadata = new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 0)));
        Mockito.verify(songMetadataDtoTransformer).transform(expectedSongMetadata);
    }

    @Test
    void transform() {
        SongMetadata sourceSongMetadata = new SongMetadata(SongType.DEFAULT, 1, new Song("", new Album("", 0)));
        Song sourceSong = new Song(1L, 0,"SONG_NAME", sourceSongMetadata, new Album("", 0));
        SongMetadataDto sourceSongMetadataDto = new SongMetadataDto(SongType.DEFAULT, 1);
        Mockito.when(songMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceSongMetadataDto);

        SongDto actual = songDtoTransformer.transform(sourceSong);

        SongDto expected = new SongDto(1L, 0, "SONG_NAME", new SongMetadataDto(SongType.DEFAULT, 1));
        assertEquals(expected, actual);
    }

}
