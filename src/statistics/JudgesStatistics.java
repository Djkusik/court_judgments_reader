package statistics;

import model.Item;
import model.Judge;
import model.Judgment;

import java.util.HashMap;
import java.util.Map;

public class JudgesStatistics {
    private Map<Judge, Integer> numberOfJudgeCases;
    private Map<Integer, Integer> numberofJudgesForJudgment;

    public JudgesStatistics() {
        this.numberOfJudgeCases = new HashMap<>();
        this.numberofJudgesForJudgment = new HashMap<>();
    }

    public void makeStatsOfCases(Judgment judgments) {
        judgments.getItems()
                .stream()
                .map(Item::getJudges)
                .forEach(judges ->
                        judges.stream()
                                .forEach(judge -> numberOfJudgeCases.merge(judge, 1, (a, b) -> a + b))
                );
    }

    public void makeStatsOfJudges(Judgment judgments) {
        judgments.getItems()
                .stream()
                .map(item -> item.getJudges().size())
                .forEach(num -> numberofJudgesForJudgment.merge(num, 1, (a, b) -> a + b));
    }

    public void getTop10Judges() {
        numberOfJudgeCases.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey().getName() + " <=> " + entry.getValue()));
    }

    public Map<Judge, Integer> getNumberOfJudgeCases() {
        return numberOfJudgeCases;
    }

    public Map<Integer, Integer> getNumberofJudgesForJudgment() {
        return numberofJudgesForJudgment;
    }


}
