import parser.Parser;
import shell.Shell;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Shell.clearScreen();

        if (args.length > 0) {
            try {
                Parser parser = new Parser();
                if (args.length == 2) {
                    parser = parser.parse(args[0], args[1]);
                } else {
                    parser = parser.parse(args[0], null);
                }
                Shell shell = new Shell(parser);
                shell.run();
            } catch (IOException err) {
                System.out.println("Podana ścieżka jest błędna");
            }
        } else
            System.out.println("Poprawną formą jest podanie ścieżki do folderu ze źródłem oraz opcjonalny plik z zapisem logu użytkowania");
    }
}
