package statistics;

import model.Item;
import model.Judgment;
import model.ReferencedRegulation;

import java.util.HashMap;
import java.util.Map;

public class JudgmentStatistics {

    private Map<String, Integer> numberOfCasesInMonth;
    private Map<String, Integer> numberOfCasesInCourts;
    private Map<ReferencedRegulation, Integer> numberOfReferencedRegulations;

    public JudgmentStatistics(Judgment judgment) {
        this.numberOfCasesInMonth = new HashMap<>();
        this.numberOfCasesInCourts = new HashMap<>();
        this.numberOfReferencedRegulations = new HashMap<>();
        this.makeStats(judgment);
    }

    public void makeStats(Judgment judgment) {
        this.makeStatsOfCasesInCourts(judgment);
        this.makeStatsOfCasesInMonth(judgment);
        this.makeStatsOfReferencedRegulations(judgment);
    }

    public void makeStatsOfCasesInMonth(Judgment judgments) {
        judgments.getItems()
                .forEach(item -> {
                    String month = item.getJudgmentDate().substring(5, 7);
                    numberOfCasesInMonth.merge(month, 1, (a, b) -> a + b);
                });
    }


    public void makeStatsOfCasesInCourts(Judgment judgments) {
        judgments.getItems()
                .forEach(item -> {
                    numberOfCasesInCourts.merge(item.getCourtType(), 1, (a, b) -> a + b);
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

    public Map<String, Integer> getNumberOfCasesInCourts() {
        return numberOfCasesInCourts;
    }
}
