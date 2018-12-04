package nameItProperlyLater;

import model.CourtCase;
import model.Item;
import model.Judge;
import model.Judgment;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class DataFinder {

    public Item findSignature(Judgment list, String searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getCourtCases()
                                .parallelStream()
                                .anyMatch(courtCase -> courtCase.getCaseNumber().equals(searched))
                ).collect(toSingleton());
    }

    public Map<String, Item> findSignatures(Judgment list, List<String> searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getCourtCases()
                                .parallelStream()
                                .allMatch(courtCase -> searched.contains(courtCase.getCaseNumber()))
                ).collect(Collectors.toMap(CourtCase::getCaseNumber, Function.identity()));
    }

    public TreeMap<String, Aggregate> numberOfCases(Judgment list, String searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getJudges()
                                .parallelStream()
                                .allMatch(judges -> judges.getName().equals(searched))
                ).collect(Collectors.toMap(
                        Judge::getName,
                        judge -> new Aggregate(judge.getName(), 1),
                        (a, b) -> new Aggregate(b.name, a.count + 1),
                        TreeMap::new)
                );
    }

    // Change to sortedTreeMap

    public TreeMap<String, Aggregate> bestJudges(Judgment list) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getJudges()
                                .parallelStream()
                                .collect(Collectors.toMap(
                                        Judge::getName,
                                        judge -> new Aggregate(judge.getName(), 1),
                                        (a, b) -> new Aggregate(b.name, a.count + 1),
                                        TreeMap::new)
                                ));
                )
    }

    public List<Item> findJudges(Judgment list, String searched) {
        return list.getItems()
                .parallelStream()
                .filter(item ->
                        item.getJudges()
                                .parallelStream()
                                .allMatch(judges -> judges.getName().equals(searched))
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

    public static class Aggregate {

        public String name;
        public int count;
    }
}
