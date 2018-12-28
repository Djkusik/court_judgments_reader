package finder;

import parser.Parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public enum Commands {
    help {
        @Override
        public void execute(Parser parser) {
            System.out.println("rubrum - wyświetlenie metryki jednego lub wielu orzeczeń, na podstawie sygnatury");
            System.out.println("content - wyświetlenie uzasadnienia dla wybranej sygnatury");
            System.out.println("judge - wyświetlenie liczby orzeczeń dla wybranego sędziego");
            System.out.println("judges - wyświetlenie 10 sędziów, którzy wydali najwięcej orzeczeń");
            System.out.println("months - wyświetlenie liczby orzeczeń w poszczególnych miesiącach (rozkład statystyczny)");
            System.out.println("courts - wyświetlenie liczby orzeczeń ze względu na typ sądu (rozkład statystyczny)");
            System.out.println("regulations - wyświetlenie 10 najczęściej przywoływanych ustaw");
            System.out.println("jury - wyświetlenie liczby spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)");
            System.out.println("quit - zamknie program");
        }
    },
    rubrum {
        @Override
        public void execute(Parser parser) {
            String prompt = "Podaj sygnatury orzeczeń (oddzielone przecinkami): ";
            System.out.println(prompt);
            String input = this.scanner();
            String[] split = input.split("\\s*,\\s*");
            List<String> strings = Arrays.asList(split);
            String result = parser.getCollector().rubrum(parser.getJudgments(), strings);

            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), prompt + "\n" + result);
            }
        }
    },
    content {
        @Override
        public void execute(Parser parser) {
            String prompt = "Podaj sygnaturę orzeczenia: ";
            System.out.println(prompt);
            String input = this.scanner();
            String result = parser.getCollector().judgmentContent(parser.getJudgments(), input);
            System.out.println(result);

            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), prompt + "\n" + result);
            }
        }
    },
    judge {
        @Override
        public void execute(Parser parser) {
            String prompt = "Podaj imię i nazwisko sędziego: ";
            System.out.println(prompt);
            String input = this.scanner();
            String result = parser.getCollector().numberOfJudgments(parser.getJudgesStats(), input);
            System.out.println(result);

            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), prompt + "\n" + result);
            }
        }
    },
    judges {
        @Override
        public void execute(Parser parser) {
            String result = parser.getCollector().bestTenJudges(parser.getJudgesStats());
            System.out.println(result);
            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), result);
            }
        }
    },
    months {
        @Override
        public void execute(Parser parser) {
            String result = parser.getCollector().monthDistribution(parser.getJudgmentsStats());
            System.out.println(result);
            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), result);
            }
        }
    },
    courts {
        @Override
        public void execute(Parser parser) {
            String result = parser.getCollector().courtDistribution(parser.getJudgmentsStats());
            System.out.println(result);
            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), result);
            }
        }
    },
    regulations {
        @Override
        public void execute(Parser parser) {
            String result = parser.getCollector().bestTenRegulations(parser.getJudgmentsStats());
            System.out.println(result);
            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), result);
            }
        }
    },
    jury {
        @Override
        public void execute(Parser parser) {
            String result = parser.getCollector().judgesDistribution(parser.getJudgesStats());
            System.out.println(result);
            if(parser.getSaveToFile() != null) {
                this.saveToFile(parser.getSaveToFile(), result);
            }
        }
    },
    quit {
        @Override
        public void execute(Parser parser) {
            System.exit(0);
        }
    };

    public static void saveToFile(String file, String output) {
        try(FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(output);
            printWriter.println();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public String scanner() {
        String input = new Scanner(System.in).nextLine();
        System.out.println();
        return input;
    }

    public abstract void execute(Parser parser);
}
