package finder;

import model.Item;
import model.Judge;
import model.Judgment;
import statistics.JudgesStatistics;
import statistics.JudgmentStatistics;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class Collector {

    private SignatureFinder finder = new SignatureFinder();
    private String[] Months = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};

    public String rubrum(Judgment list, List<String> searched) {
        List<Item> found = finder.findSignatures(list, searched);
        String result = "";

        for (Item present : found) {
            System.out.println("Sygnatura: " + present.getCourtCases().get(0).getCaseNumber());
            result = result + "Sygnatura: " + present.getCourtCases().get(0).getCaseNumber() + "\n";
            System.out.println("Data wydania orzeczenia: " + present.getJudgmentDate());
            result = result + "Data wydania orzeczenia: " + present.getJudgmentDate() + "\n";
            System.out.println("Rodzaj sądu:" + present.getCourtType());
            result = result + "Rodzaj sądu:" + present.getCourtType() + "\n";
            System.out.println("Sędziowie:");
            result = result + "Sędziowie:" + "\n";
            for (Judge judge : present.getJudges()) {
                System.out.println(judge.getName());
                result = result + judge.getName() + "\n";
                System.out.print("Role: ");
                result = result + "Role: " + "\n";
                for (String role : judge.getSpecialRoles()) {
                    System.out.print(role + " ");
                    result = result + role + " ";
                }
                System.out.println();
            }
        }

        return result;
    }

    public String judgmentContent(Judgment list, String searched) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);
        Item found = finder.findSignature(list, searched);

        System.out.println("Uzasadnienie: " + found.getTextContent());
        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String numberOfJudgments(JudgesStatistics stats, String name) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);
        Integer number = stats.getNumberOfJudgeCases().get(name);
        System.out.println(name + "brał udział w " + number + " orzeczeń");

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String bestTenJudges(JudgesStatistics stats) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);

        stats.getTop10Judges();

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String monthDistribution(JudgmentStatistics stats) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);

        System.out.println(stats.getNumberOfCasesInMonth());

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String courtDistribution(JudgmentStatistics stats) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);

        System.out.println(stats.getNumberOfCasesInCourts());

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String bestTenRegulations(JudgmentStatistics stats) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);

        stats.getTop10Regulations();

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }

    public String judgesDistribution(JudgesStatistics stats) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(result);
        PrintStream old = System.out;
        System.setOut(ps);

        System.out.println(stats.getNumberofJudgesForJudgment());

        System.out.flush();
        System.setOut(old);
        return result.toString();
    }
}
