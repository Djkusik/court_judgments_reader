package parser;

import com.google.gson.Gson;
import model.Judgment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public Judgment parse(String path) {
        Gson parseToObject = new Gson();

        try (BufferedReader json = new BufferedReader(new FileReader(path))){
            Judgment judgment = parseToObject.fromJson(json, Judgment.class);
            return judgment;
        } catch (IOException err) {
            err.printStackTrace();
        }
        return null;
    }
}
