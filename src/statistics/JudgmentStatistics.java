package statistics;

import model.Item;
import model.Judgment;
import model.ReferencedRegulation;

import java.util.HashMap;
import java.util.Map;

public class JudgmentStatistics {

    private Map<String, Integer> numberOfCasesInMonth;
    private Map<ReferencedRegulation, Integer> numberOfReferencedRegulations;

    public JudgmentStatistics() {
        this.numberOfCasesInMonth = new HashMap<>();
        this.numberOfReferencedRegulations = new HashMap<>();
    }

    public void makeStatsOfCasesInMonth(Judgment judgments) {
        judgments.getItems()
                .forEach(item -> {
                    String month = item.getJudgmentDate().substring(5, 7);
                    numberOfCasesInMonth.merge(month, 1, (a, b) -> a + b);
                });
    }

    public void makeStatsOfReferencedRegulations(Judgment judgments) {
        judgments.getItems()
                .stream()
                .map(Item::getReferencedRegulations)
                .forEach(regulations ->
                        regulations.stream()
                                .forEach(regulation -> numberOfReferencedRegulations.merge(regulation, 1, (a, b) -> a + b))
                );
    }

    public void getTop10Regulations() {
        numberOfReferencedRegulations.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey().getJournalTitle() + " <=> " + entry.getValue()));
    }

    public Map<String, Integer> getNumberOfCasesInMonth() {
        return numberOfCasesInMonth;
    }

    public Map<ReferencedRegulation, Integer> getNumberOfReferencedRegulations() {
        return numberOfReferencedRegulations;
    }

}
