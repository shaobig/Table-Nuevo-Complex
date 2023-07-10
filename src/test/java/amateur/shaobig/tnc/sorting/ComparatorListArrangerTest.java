package amateur.shaobig.tnc.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComparatorListArrangerTest {

    private Comparator<StubClass> comparator;

    private ComparatorListArranger<StubClass> comparatorListArranger;

    @BeforeEach
    void init() {
        this.comparator = Mockito.mock(Comparator.class);

        this.comparatorListArranger = new ComparatorListArranger<>(comparator);
    }

    @Test
    void arrangeCheckComparatorFirstArgument() {
        List<StubClass> sourceList = List.of(new StubClass(1), new StubClass(2), new StubClass(3));

        comparatorListArranger.arrange(sourceList);
        ArgumentCaptor<StubClass> stubClassArgumentCaptor = ArgumentCaptor.forClass(StubClass.class);
        Mockito.verify(comparator, Mockito.atLeastOnce()).compare(stubClassArgumentCaptor.capture(), Mockito.any());
        List<StubClass> actualList = stubClassArgumentCaptor.getAllValues();

        List<StubClass> expectedList = List.of(new StubClass(2), new StubClass(3));
        assertEquals(expectedList, actualList);
    }

    @Test
    void arrangeCheckComparatorSecondArgument() {
        List<StubClass> sourceList = List.of(new StubClass(1), new StubClass(2), new StubClass(3));

        comparatorListArranger.arrange(sourceList);
        ArgumentCaptor<StubClass> stubClassArgumentCaptor = ArgumentCaptor.forClass(StubClass.class);
        Mockito.verify(comparator, Mockito.atLeastOnce()).compare(Mockito.any(), stubClassArgumentCaptor.capture());
        List<StubClass> actualList = stubClassArgumentCaptor.getAllValues();

        List<StubClass> expectedList = List.of(new StubClass(1), new StubClass(2));
        assertEquals(expectedList, actualList);
    }

    static Stream<Arguments> arrangeInputData() {
        return Stream.of(
                Arguments.of(List.of(new StubClass(1)), List.of(new StubClass(1))),
                Arguments.of(List.of(new StubClass(1), new StubClass(2)), List.of(new StubClass(1), new StubClass(2))),
                Arguments.of(List.of(new StubClass(2), new StubClass(1)), List.of(new StubClass(1), new StubClass(2))),
                Arguments.of(List.of(new StubClass(1), new StubClass(2), new StubClass(3)), List.of(new StubClass(1), new StubClass(2), new StubClass(3))),
                Arguments.of(List.of(new StubClass(3), new StubClass(2), new StubClass(1)), List.of(new StubClass(1), new StubClass(2), new StubClass(3))),
                Arguments.of(List.of(new StubClass(1), new StubClass(3), new StubClass(2)), List.of(new StubClass(1), new StubClass(2), new StubClass(3))),
                Arguments.of(List.of(new StubClass(3), new StubClass(1), new StubClass(2)), List.of(new StubClass(1), new StubClass(2), new StubClass(3))),
                Arguments.of(List.of(new StubClass(1), new StubClass(1), new StubClass(2)), List.of(new StubClass(1), new StubClass(1), new StubClass(2))),
                Arguments.of(List.of(new StubClass(2), new StubClass(1), new StubClass(1)), List.of(new StubClass(1), new StubClass(1), new StubClass(2)))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "arrangeInputData")
    void arrange(List<StubClass> sourceList, List<StubClass> expected) {
        StubClass sourceFirstStubClassObject = new StubClass(1);
        StubClass sourceSecondStubClassObject = new StubClass(2);
        StubClass sourceThirdStubClassObject = new StubClass(3);
        Mockito.when(comparator.compare(sourceFirstStubClassObject, sourceFirstStubClassObject)).thenReturn(0);
        Mockito.when(comparator.compare(sourceFirstStubClassObject, sourceSecondStubClassObject)).thenReturn(-1);
        Mockito.when(comparator.compare(sourceFirstStubClassObject, sourceThirdStubClassObject)).thenReturn(-1);
        Mockito.when(comparator.compare(sourceSecondStubClassObject, sourceFirstStubClassObject)).thenReturn(1);
        Mockito.when(comparator.compare(sourceSecondStubClassObject, sourceThirdStubClassObject)).thenReturn(-1);
        Mockito.when(comparator.compare(sourceThirdStubClassObject, sourceFirstStubClassObject)).thenReturn(1);
        Mockito.when(comparator.compare(sourceThirdStubClassObject, sourceSecondStubClassObject)).thenReturn(1);

        List<StubClass> actual = comparatorListArranger.arrange(sourceList);

        assertEquals(expected, actual);
    }

    private static record StubClass(int id) {

    }

}
