package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.SongMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class FullAlbumMarkCalculator implements Calculator<SongMetadata> {

    private final AverageCalculator<Integer> averageCalculator;

    @Override
    public BigDecimal calculate(List<SongMetadata> metadataList) {
        List<Integer> marks = metadataList.stream()
                .map(SongMetadata::getMark)
                .toList();
        return getAverageCalculator().calculate(marks);
    }

}