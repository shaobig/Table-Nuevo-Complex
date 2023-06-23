package amateur.shaobig.tnc.transformer.song.metadata;

import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongMetadataDtoTransformerTest {

    private SongMetadataDtoTransformer songMetadataDtoTransformer;

    @BeforeEach
    void init() {
        this.songMetadataDtoTransformer = new SongMetadataDtoTransformer();
    }

    @Test
    void transform() {
        SongMetadata sourceSongMetadata = new SongMetadata(1L, SongType.DEFAULT, 1, new Song());

        SongMetadataDto actual = songMetadataDtoTransformer.transform(sourceSongMetadata);

        SongMetadataDto expected = new SongMetadataDto(SongType.DEFAULT, 1);
        assertEquals(expected, actual);
    }

}
