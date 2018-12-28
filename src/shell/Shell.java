package shell;

import model.Judgment;
import finder.Collector;
import org.jline.reader.*;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import statistics.JudgesStatistics;
import statistics.JudgmentStatistics;
import parser.Parser;

import java.util.LinkedList;
import java.util.List;

public class Shell {

    private String[] optionsList;
    private Judgment judgment;
    private Parser parser;
    private JudgesStatistics judgesStats;
    private JudgmentStatistics judgmentsStats;
    private Collector collector;

    public Shell (String pathToParse) {
        this.judgment = new Judgment();
        this.parser = new Parser();
        judgment = parser.parse(pathToParse);
    }

    public Shell (String pathToParse, String pathToSaveLog) {
        this.judgment = new Judgment();
        this.parser = new Parser();
        judgment = parser.parse(pathToParse);
    }

    public void init() {
        optionsList = new String[] { "help", "rubrum", "content", "judge", "judges", "months", "courts", "regulations", "jury" };
    }

    public void run() {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        List<Completer> completors = new LinkedList<Completer>();
        completors.add(new StringsCompleter(optionsList));

        LineReaderBuilder reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completors)
                .build();

        String line;
        String prompt = "$> ";

        while((line = readLine(reader, "")) != null) {

        }

    }

    private String readLine(LineReader reader, String promtMessage) {
        try {
            String line = reader.readLine(promtMessage + "\nshell> ");
            return line.trim();
        }
        catch (UserInterruptException e) {
            return null;
        }
        catch (EndOfFileException e) {
            return null;
        }
    }

    private void printHelp() {
        System.out.println("rubrum - wyświetlenie metryki jednego lub wielu orzeczeń, na podstawie sygnatury");
        System.out.println("content - wyświetlenie uzasadnienia dla wybranej sygnatury");
        System.out.println("judge - wyświetlenie liczby orzeczeń dla wybranego sędziego");
        System.out.println("judges - wyświetlenie 10 sędziów, którzy wydali najwięcej orzeczeń");
        System.out.println("months - wyświetlenie liczby orzeczeń w poszczególnych miesiącach (rozkład statystyczny)");
        System.out.println("courts - wyświetlenie liczby orzeczeń ze względu na typ sądu (rozkład statystyczny)");
        System.out.println("regulations - wyświetlenie 10 najczęściej przywoływanych ustaw");
        System.out.println("jury - wyświetlenie liczby spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)");
    }
}
