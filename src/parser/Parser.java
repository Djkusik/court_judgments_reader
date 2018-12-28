package parser;

import model.Judgment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

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

    public Judgment parse(String path) {
        List<Path> paths = this.openFile(path);
        JsonParser jsonParser = new JsonParser();
        HtmlParser htmlParser = new HtmlParser();
        Judgment judgment = new Judgment();

        for (Path currentPath : paths) {
            if (currentPath.toString().endsWith(".json")) {
                judgment.add(jsonParser.parse(currentPath));
            } else if (currentPath.toString().endsWith(".html")) {
                judgment.add(htmlParser.parse(currentPath));
            }
        }
        return judgment;
    }
}
