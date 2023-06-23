package amateur.shaobig.tnc.transformer.album.metadata;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumMetadataDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = new AlbumMetadataDtoTransformer();
    }

    @Test
    void transform() {
        AlbumMetadata sourceAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album());

        AlbumMetadataDto actual = albumMetadataDtoTransformer.transform(sourceAlbumMetadata);

        AlbumMetadataDto expected = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        assertEquals(expected, actual);
    }

}
