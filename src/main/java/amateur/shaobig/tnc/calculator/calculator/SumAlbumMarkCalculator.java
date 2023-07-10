package amateur.shaobig.tnc.calculator.calculator;

import amateur.shaobig.tnc.calculator.mark.BasicMarkResolver;
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
public class SumAlbumMarkCalculator implements Calculator<SongMetadata> {

    private final BasicMarkResolver basicMarkResolver;
    private final IntegerSumCalculator integerSumCalculator;

    @Override
    public BigDecimal calculate(List<SongMetadata> metadataList) {
        return getIntegerSumCalculator().calculate(getBasicMarkResolver().resolveMarks(metadataList));
    }

}
