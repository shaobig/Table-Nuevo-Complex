package amateur.shaobig.tnc.transformer.album.statistics;

import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.calculator.AverageAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.BasicAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.FullAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.RelativeSumFinalAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.SumAlbumMarkCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongMetadataListAlbumStatisticsDtoTransformerTest {

    private FullAlbumMarkCalculator fullAlbumMarkCalculator;
    private BasicAlbumMarkCalculator basicAlbumMarkCalculator;
    private AverageAlbumMarkCalculator averageAlbumMarkCalculator;
    private SumAlbumMarkCalculator sumAlbumMarkCalculator;
    private RelativeSumFinalAlbumMarkCalculator relativeSumFinalAlbumMarkCalculator;

    private SongMetadataListAlbumStatisticsDtoTransformer songMetadataListAlbumStatisticsDtoTransformer;

    @BeforeEach
    void init() {
        this.fullAlbumMarkCalculator = Mockito.mock(FullAlbumMarkCalculator.class);
        this.basicAlbumMarkCalculator = Mockito.mock(BasicAlbumMarkCalculator.class);
        this.averageAlbumMarkCalculator = Mockito.mock(AverageAlbumMarkCalculator.class);
        this.sumAlbumMarkCalculator = Mockito.mock(SumAlbumMarkCalculator.class);
        this.relativeSumFinalAlbumMarkCalculator = Mockito.mock(RelativeSumFinalAlbumMarkCalculator.class);

        this.songMetadataListAlbumStatisticsDtoTransformer = new SongMetadataListAlbumStatisticsDtoTransformer(fullAlbumMarkCalculator, basicAlbumMarkCalculator, averageAlbumMarkCalculator, sumAlbumMarkCalculator, relativeSumFinalAlbumMarkCalculator);
    }

    @Test
    void transformCheckFullAlbumMarkCalculator() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        List<SongMetadata> expectedSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.verify(fullAlbumMarkCalculator).calculate(expectedSongMetadataList);
    }

    @Test
    void transformCheckBasicAlbumMarkCalculator() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        List<SongMetadata> expectedSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.verify(basicAlbumMarkCalculator).calculate(expectedSongMetadataList);
    }

    @Test
    void transformCheckAverageAlbumMarkCalculator() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        List<BigDecimal> expectedMarkList = List.of(BigDecimal.ONE, BigDecimal.ONE);
        Mockito.verify(averageAlbumMarkCalculator).calculate(expectedMarkList);
    }

    @Test
    void transformCheckSumAlbumMarkCalculator() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        List<SongMetadata> expectedSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1, new Song("", new Album("", 0))));
        Mockito.verify(sumAlbumMarkCalculator).calculate(expectedSongMetadataList);
    }

    @Test
    void transformCheckTotalMark() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(averageAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        BigDecimal expectedTotalMark = BigDecimal.ONE;
        Mockito.verify(relativeSumFinalAlbumMarkCalculator).calculate(Mockito.eq(expectedTotalMark), Mockito.any());
    }

    @Test
    void transformCheckSumAlbumMark() {
        List<SongMetadata> sourceSongMetadataList = List.of(new SongMetadata(1L, SongType.DEFAULT, 1));
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);
        Mockito.when(sumAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.ONE);

        songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        BigDecimal expectedSumAlbumMark = BigDecimal.ONE;
        Mockito.verify(relativeSumFinalAlbumMarkCalculator).calculate(Mockito.any(), Mockito.eq(expectedSumAlbumMark));
    }
    
    @Test
    void transform() {
        List<SongMetadata> sourceSongMetadataList = List.of();
        Mockito.when(fullAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.valueOf(1));
        Mockito.when(basicAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.valueOf(2));
        Mockito.when(averageAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.valueOf(3));
        Mockito.when(sumAlbumMarkCalculator.calculate(Mockito.anyList())).thenReturn(BigDecimal.valueOf(4));
        Mockito.when(relativeSumFinalAlbumMarkCalculator.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(5));

        AlbumStatisticsDto actual = songMetadataListAlbumStatisticsDtoTransformer.transform(sourceSongMetadataList);

        AlbumStatisticsDto expected = new AlbumStatisticsDto(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5));
        assertEquals(expected, actual);
    }
    
}
