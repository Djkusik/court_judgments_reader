package shell;

import org.jline.reader.Completer;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.LinkedList;
import java.util.List;

public class Shell {

    private String[] optionsList;

    public void init() {
        optionsList = new String[] [ "1", "2", "3", "4", "5", "6", "7", "8" ];
    }

    public void run() {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        LineReaderBuilder readerBuilder = LineReaderBuilder.builder();
        List<Completer> completors = new LinkedList<Completer>();
    }

    private void printHelp() {
        System.out.println()
    }
}
