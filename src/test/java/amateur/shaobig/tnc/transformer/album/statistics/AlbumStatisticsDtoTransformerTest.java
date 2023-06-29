package amateur.shaobig.tnc.transformer.album.statistics;

import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.SongType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumStatisticsDtoTransformerTest {

    private SongMetadataListAlbumStatisticsDtoTransformer songMetadataListAlbumStatisticsDtoTransformer;

    private AlbumStatisticsDtoTransformer albumStatisticsDtoTransformer;

    @BeforeEach
    void init() {
        this.songMetadataListAlbumStatisticsDtoTransformer = Mockito.mock(SongMetadataListAlbumStatisticsDtoTransformer.class);

        this.albumStatisticsDtoTransformer = new AlbumStatisticsDtoTransformer(songMetadataListAlbumStatisticsDtoTransformer);
    }

    @Test
    void transformCheckSongMetadataListAlbumStatisticsDtoTransformer() {
        List<Song> sourceAlbumSongs = List.of(new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album()),
                new Song(1L, 0, "", new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))), new Album()));
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), sourceAlbumSongs);

        albumStatisticsDtoTransformer.transform(sourceAlbum);

        List<SongMetadata> expectedSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME_1", new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))),
                new SongMetadata(1L, SongType.DEFAULT, 1, new Song(1L, 0, "SONG_NAME_2", new SongMetadata(1L, SongType.DEFAULT, 1, new Song()), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));
        Mockito.verify(songMetadataListAlbumStatisticsDtoTransformer).transform(expectedSongMetadataList);
    }

    @Test
    void transform() {
        AlbumStatisticsDto sourceAlbumStatisticsDto = new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.when(songMetadataListAlbumStatisticsDtoTransformer.transform(Mockito.anyList())).thenReturn(sourceAlbumStatisticsDto);

        AlbumStatisticsDto actual = albumStatisticsDtoTransformer.transform(sourceAlbum);

        AlbumStatisticsDto expected = new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        assertEquals(expected, actual);
    }

}
