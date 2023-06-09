package amateur.shaobig.tnc.transformer.album.calculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AverageAlbumMarkCalculator implements Calculator<BigDecimal> {

    private final BigDecimalDivideCalculator bigDecimalDivideCalculator;

    @Override
    public BigDecimal calculate(List<BigDecimal> marks) {
        return getBigDecimalDivideCalculator().calculate(marks);
    }

}
