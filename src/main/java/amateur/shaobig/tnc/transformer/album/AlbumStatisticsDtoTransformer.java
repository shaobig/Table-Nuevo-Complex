package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.calculator.BasicAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.FullAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.SumAlbumMarkCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.AverageAlbumMarkCalculator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumStatisticsDtoTransformer implements Transformer<Album, AlbumStatisticsDto> {

    private final FullAlbumMarkCalculator fullAlbumMarkCalculator;
    private final BasicAlbumMarkCalculator basicAlbumMarkCalculator;
    private final AverageAlbumMarkCalculator averageAlbumMarkCalculator;
    private final SumAlbumMarkCalculator sumAlbumMarkCalculator;

    @Override
    public AlbumStatisticsDto transform(Album album) {
        List<SongMetadata> metadataList = album.getSongs().stream()
                .map(Song::getMetadata)
                .toList();
        BigDecimal fullAlbumMark = getFullAlbumMarkCalculator().calculate(metadataList);
        BigDecimal basicAlbumMark = getBasicAlbumMarkCalculator().calculate(metadataList);
        BigDecimal averageAlbumMark = getAverageAlbumMarkCalculator().calculate(List.of(fullAlbumMark, basicAlbumMark));
        BigDecimal sumAlbumMark = getSumAlbumMarkCalculator().calculate(metadataList);
        return new AlbumStatisticsDto(fullAlbumMark, basicAlbumMark, averageAlbumMark, sumAlbumMark);
    }

}
