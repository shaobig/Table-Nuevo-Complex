package amateur.shaobig.tnc.transformer.album.statistics;

import amateur.shaobig.tnc.calculator.calculator.AverageAlbumMarkCalculator;
import amateur.shaobig.tnc.calculator.calculator.BasicAlbumMarkCalculator;
import amateur.shaobig.tnc.calculator.calculator.FullAlbumMarkCalculator;
import amateur.shaobig.tnc.calculator.calculator.RelativeSumFinalAlbumMarkCalculator;
import amateur.shaobig.tnc.calculator.calculator.SumAlbumMarkCalculator;
import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.transformer.Transformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
class SongMetadataListAlbumStatisticsDtoTransformer implements Transformer<List<SongMetadata>, AlbumStatisticsDto> {

    private final FullAlbumMarkCalculator fullAlbumMarkCalculator;
    private final BasicAlbumMarkCalculator basicAlbumMarkCalculator;
    private final AverageAlbumMarkCalculator averageAlbumMarkCalculator;
    private final SumAlbumMarkCalculator sumAlbumMarkCalculator;
    private final RelativeSumFinalAlbumMarkCalculator relativeSumFinalAlbumMarkCalculator;

    @Override
    public AlbumStatisticsDto transform(List<SongMetadata> metadataList) {
        BigDecimal fullAlbumMark = getFullAlbumMarkCalculator().calculate(metadataList);
        BigDecimal basicAlbumMark = getBasicAlbumMarkCalculator().calculate(metadataList);
        BigDecimal averageAlbumMark = getAverageAlbumMarkCalculator().calculate(List.of(fullAlbumMark, basicAlbumMark));
        BigDecimal sumAlbumMark = getSumAlbumMarkCalculator().calculate(metadataList);
        BigDecimal finalMark = getRelativeSumFinalAlbumMarkCalculator().calculate(averageAlbumMark, sumAlbumMark);
        return new AlbumStatisticsDto(fullAlbumMark, basicAlbumMark, averageAlbumMark, sumAlbumMark, finalMark);
    }

}
