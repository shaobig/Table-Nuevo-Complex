package amateur.shaobig.tnc.transformer.album.calculator;

import org.springframework.stereotype.Component;

@Component
class IntegerDivideCalculator extends DivideCalculator<Integer> {

    public IntegerDivideCalculator(IntegerSumCalculator sumCalculator, ListSizeCalculator<Integer> sizeDivideCalculator) {
        super(sumCalculator, sizeDivideCalculator);
    }

}
