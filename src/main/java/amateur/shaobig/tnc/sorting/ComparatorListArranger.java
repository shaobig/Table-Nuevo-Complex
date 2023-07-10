package amateur.shaobig.tnc.sorting;

import java.util.Comparator;
import java.util.List;

public class ComparatorListArranger<E> implements ListArranger<E> {
    
    private Comparator<E> comparator;

    public ComparatorListArranger(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<E> arrange(List<E> entityList) {
        return entityList.stream()
                .sorted(getComparator())
                .toList();
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

}
