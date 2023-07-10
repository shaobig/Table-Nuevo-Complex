package amateur.shaobig.tnc.sorting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ComparatorListArranger<E> implements ListArranger<E> {

    private final Comparator<E> comparator;

    @Override
    public List<E> arrange(List<E> entityList) {
        return entityList.stream()
                .sorted(getComparator())
                .toList();
    }

}
