package amateur.shaobig.tnc.transformer.album.calculator;

import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.transformer.album.calculator.mark.BasicMarkResolver;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class SumAlbumMarkCalculator implements Calculator<SongMetadata> {

    private final BasicMarkResolver basicMarkResolver;
    private final IntegerSumCalculator sumCalculator;

    @Override
    public BigDecimal calculate(List<SongMetadata> metadataList) {
        return getSumCalculator().calculate(getBasicMarkResolver().resolveMarks(metadataList));
    }

}
