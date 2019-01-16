package shell;

import finder.Commands;
import org.jline.reader.*;
import org.jline.reader.impl.completer.EnumCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import parser.Parser;

import java.io.IOException;

public class Shell {

    private Parser parser;

    public Shell(Parser parser) {
        this.parser = parser;
    }

    public void run() throws IOException {
        this.clearScreen();

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .name("Court_Judgments_Reader")
                .build();

        Completer completors = new EnumCompleter(Commands.class);

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completors)
                .build();
        System.out.println("Aby poznać komendy, wpisz help");
        while (true) {
            String input = lineReader.readLine("Twoja komenda: ");
            try {
                if(parser.getSaveToFile() != null) {
                    Commands.saveToFile(parser.getSaveToFile(), "Wpisana komenda: " + input);
                }

                Commands.valueOf(input.toLowerCase()).execute(parser);
            } catch (IllegalArgumentException err) {
                if(parser.getSaveToFile() != null) {
                    Commands.saveToFile(parser.getSaveToFile(), "Nieobsługiwana komenda");
                }
                System.out.println("Nieobsługiwana komenda");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
