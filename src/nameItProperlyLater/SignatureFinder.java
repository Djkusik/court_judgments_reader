package nameItProperlyLater;

import model.Item;
import model.Judgment;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SignatureFinder {

    public Item findSignature(Judgment list, String searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getCourtCases()
                                .parallelStream()
                                .anyMatch(courtCase -> courtCase.getCaseNumber().equals(searched))
                ).collect(toSingleton());
    }

    public List<Item> findSignatures(Judgment list, List<String> searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getCourtCases()
                                .parallelStream()
                                .allMatch(courtCase -> searched.contains(courtCase.getCaseNumber()))
                ).collect(Collectors.toList());
    }


    // New Collector to be able to return one element
    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

}
