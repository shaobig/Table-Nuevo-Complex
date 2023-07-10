package amateur.shaobig.tnc.configuration;

import amateur.shaobig.tnc.calculator.calculator.AverageCalculator;
import amateur.shaobig.tnc.calculator.calculator.BigDecimalSumCalculator;
import amateur.shaobig.tnc.calculator.calculator.IntegerSumCalculator;
import amateur.shaobig.tnc.calculator.calculator.ListSizeCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AlbumStatisticsCalculatorConfiguration {

    @Bean
    public AverageCalculator<Integer> integerAverageCalculator(IntegerSumCalculator integerSumCalculator, ListSizeCalculator<Integer> listSizeCalculator) {
        return new AverageCalculator<>(integerSumCalculator, listSizeCalculator);
    }

    @Bean
    public AverageCalculator<BigDecimal> bigDecimalAverageCalculator(BigDecimalSumCalculator bigDecimalSumCalculator, ListSizeCalculator<BigDecimal> listSizeCalculator) {
        return new AverageCalculator<>(bigDecimalSumCalculator, listSizeCalculator);
    }

}
