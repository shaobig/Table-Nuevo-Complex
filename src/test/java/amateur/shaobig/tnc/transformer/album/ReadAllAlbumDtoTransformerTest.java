package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadAllAlbumDtoTransformerTest {

    private ReadAllAlbumDtoTransformer readAllAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.readAllAlbumDtoTransformer = new ReadAllAlbumDtoTransformer();
    }

    @Test
    void transform() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "", ""), List.of()), List.of(), List.of());

        ReadAllAlbumDto actual = readAllAlbumDtoTransformer.transform(sourceAlbum);

        ReadAllAlbumDto expected = new ReadAllAlbumDto(1L, "ARTIST_NAME", "COUNTRY_NAME", "ALBUM_NAME", AlbumType.LP, 0);
        assertEquals(expected, actual);
    }

}
