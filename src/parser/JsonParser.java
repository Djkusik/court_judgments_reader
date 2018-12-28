package parser;

import com.google.gson.Gson;
import model.Judgment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class JsonParser {

    public Judgment parse(Path path) {
        Gson parseToObject = new Gson();
        Judgment judgment = new Judgment();

        try (BufferedReader json = new BufferedReader(new FileReader(path.toString()))) {
                return parseToObject.fromJson(json, Judgment.class);
        } catch (IOException err) {
                err.printStackTrace();
        }
        return null;
    }
}
