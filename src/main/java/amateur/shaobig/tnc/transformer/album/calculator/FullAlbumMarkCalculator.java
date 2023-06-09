package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.SongMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class FullAlbumMarkCalculator implements Calculator<SongMetadata> {

    private final IntegerDivideCalculator integerDivideCalculator;

    @Override
    public BigDecimal calculate(List<SongMetadata> metadataList) {
        List<Integer> marks = metadataList.stream()
                .map(SongMetadata::getMark)
                .toList();
        return getIntegerDivideCalculator().calculate(marks);
    }

}
