package finder;

import model.Item;
import model.Judge;
import model.Judgment;
import statistics.JudgesStatistics;
import statistics.JudgmentStatistics;

import java.util.List;

public class Collector {

    private SignatureFinder finder = new SignatureFinder();
    private String[] Months = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};

    public void rubrum(Judgment list, List<String> searched) {
        List<Item> found = finder.findSignatures(list, searched);

        for (Item present : found) {
            System.out.println("Sygnatura: " + present.getCourtCases().get(0).getCaseNumber());
            System.out.println("Data wydania orzeczenia: " + present.getJudgmentDate());
            System.out.println("Rodzaj sądu:" + present.getCourtType());
            System.out.println("Sędziowie:");
            for (Judge judge : present.getJudges()) {
                System.out.println(judge.getName());
                System.out.print("Role: ");
                for (String role : judge.getSpecialRoles()) {
                    System.out.print(role + " ");
                }
                System.out.println();
            }
        }
    }

    public void judgmentContent(Judgment list, String searched) {
        Item found = finder.findSignature(list, searched);

        System.out.println ("Uzasadnienie: " + found.getTextContent());
    }

    public void numberOfJudgments(JudgesStatistics stats, String name) {
        Integer number = stats.getNumberOfJudgeCases().get(name);
        System.out.println(name + "brał udział w " + number + " orzeczeń");
    }

    public void bestTenJudges(JudgesStatistics stats) {
        stats.getTop10Judges();
    }

    public void monthDistribution(JudgmentStatistics stats) {
        for (int i = 1; i <= 12; ++i) {
            System.out.println(Months[i-1] + ": " + stats.getNumberOfCasesInMonth().get(i));
        }
    }

    public void courtDistribution(JudgmentStatistics stats) {
        System.out.println(stats.getNumberOfCasesInCourts());
    }

    public void bestTenRegulations(JudgmentStatistics stats) {
        stats.getTop10Regulations();
    }

    public void judgesDistribution(JudgesStatistics stats) {
        System.out.println(stats.getNumberofJudgesForJudgment());
    }
}
