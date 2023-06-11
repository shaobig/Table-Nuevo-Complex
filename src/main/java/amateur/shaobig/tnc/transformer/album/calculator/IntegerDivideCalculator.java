package amateur.shaobig.tnc.transformer.album.calculator;

import org.springframework.stereotype.Component;

@Component
class IntegerDivideCalculator extends AverageCalculator<Integer> {

    public IntegerDivideCalculator(IntegerSumCalculator sumCalculator, ListSizeCalculator<Integer> sizeDivideCalculator) {
        super(sumCalculator, sizeDivideCalculator);
    }

}
