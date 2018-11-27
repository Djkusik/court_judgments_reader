package parser;

import com.google.gson.Gson;
import model.Judgment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public Judgment parse(String path) {
        BufferedReader json = null;
        Gson parseToObject = new Gson();

        try {
            json = new BufferedReader(new FileReader(path));
            Judgment judgment = parseToObject.fromJson(json, Judgment.class);
            return judgment;
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        } finally {
            if (json != null) {
                try {
                    json.close();
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        }
        return null;
    }
}
