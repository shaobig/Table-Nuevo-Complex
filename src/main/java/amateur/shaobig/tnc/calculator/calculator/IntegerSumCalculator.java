package amateur.shaobig.tnc.calculator.calculator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class IntegerSumCalculator implements Calculator<Integer> {

    @Override
    public BigDecimal calculate(List<Integer> marks) {
        int sum = marks.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return BigDecimal.valueOf(sum);
    }

}
