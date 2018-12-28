package parser;

import finder.Collector;
import model.Judgment;
import statistics.JudgesStatistics;
import statistics.JudgmentStatistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private Judgment judgments;
    private String saveToFile;
    private JudgesStatistics judgesStats;
    private JudgmentStatistics judgmentsStats;
    private Collector collector;

    public Parser() {
        this.judgments = new Judgment();
        this.collector = new Collector();
    }

    public List<Path> openFile(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            return paths
                    .filter(p -> p.getFileName().toString().matches(".*\\.(json|html)$"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException err) {
            err.printStackTrace();
        }
        return null;
    }

    public Parser parse(String path, String saveToFile) throws IOException {
        this.checkSaveFile(saveToFile);
        List<Path> paths = this.openFile(path);
        JsonParser jsonParser = new JsonParser();
        HtmlParser htmlParser = new HtmlParser();

        for (Path currentPath : paths) {
            if (currentPath.toString().endsWith(".json")) {
                judgments.add(jsonParser.parse(currentPath));
            } else if (currentPath.toString().endsWith(".html")) {
                judgments.add(htmlParser.parse(currentPath));
            }
        }
        this.judgesStats = new JudgesStatistics(judgments);
        this.judgmentsStats = new JudgmentStatistics(judgments);

        return this;
    }

    private void checkSaveFile(String saveToFile) throws IOException {
        if (saveToFile != null) {
            File saved = new File(saveToFile);
            saved.createNewFile();
            if (saved.isDirectory()) {
                System.out.println("Podany plik to folder, zapis nie zadziała");
                this.saveToFile = null;
            } else this.saveToFile = saveToFile;
        }
    }

    public Judgment getJudgments() {
        return judgments;
    }

    public String getSaveToFile() {
        return saveToFile;
    }

    public JudgesStatistics getJudgesStats() {
        return judgesStats;
    }

    public JudgmentStatistics getJudgmentsStats() {
        return judgmentsStats;
    }

    public Collector getCollector() {
        return collector;
    }
}
