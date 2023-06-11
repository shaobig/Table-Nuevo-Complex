package amateur.shaobig.tnc.configuration;

import amateur.shaobig.tnc.transformer.album.calculator.AverageCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.BigDecimalSumCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.IntegerSumCalculator;
import amateur.shaobig.tnc.transformer.album.calculator.ListSizeCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AlbumStatisticsCalculatorConfiguration {

    @Bean
    public AverageCalculator<Integer> integerAverageCalculator(ListSizeCalculator<Integer> listSizeCalculator) {
        return new AverageCalculator<>(new IntegerSumCalculator(), listSizeCalculator);
    }

    @Bean
    public AverageCalculator<BigDecimal> bigDecimalAverageCalculator(ListSizeCalculator<BigDecimal> listSizeCalculator) {
        return new AverageCalculator<>(new BigDecimalSumCalculator(), listSizeCalculator);
    }

}
