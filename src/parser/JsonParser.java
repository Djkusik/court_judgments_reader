package parser;

import com.google.gson.Gson;
import model.Judgment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonParser {

    public List<Path> openFile(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            return paths
                    .filter(p -> p.getFileName().toString().endsWith("json"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException err) {
            err.printStackTrace();
        }
        return null;
    }


    public Judgment parse(String path) {
        List<Path> paths = this.openFile(path);
        Gson parseToObject = new Gson();
        Judgment judgment = new Judgment();

        for (Path currentPath : paths) {
            try (BufferedReader json = new BufferedReader(new FileReader(currentPath.toString()))) {
                judgment.add(parseToObject.fromJson(json, Judgment.class));
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
        return judgment;
    }
}
