package amateur.shaobig.tnc.transformer.album.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class ListSizeCalculator<T> implements Calculator<T> {

    @Override
    public BigDecimal calculate(List<T> inputData) {
        return BigDecimal.valueOf(inputData.size());
    }

}
