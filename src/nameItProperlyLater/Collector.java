package nameItProperlyLater;

import model.Item;
import model.Judge;
import model.Judgment;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Collector {

    DataFinder finder = new DataFinder();

    public void rubrum (Judgment list, List<String> searched) {
        Map<String, Item> found = finder.findSignatures(list, searched);

        String signature;
        String date;
        String courtType;
        List<Judge> judges;

        for (String object : searched) {
            signature = searched;
            date = found.get(searched).getJudgmentDate();
            courtType = found.get(searched).getCourtType();
            judges = found.get(searched).getJudges();
        }
    }

    public void judgmentContent (Judgment list, String searched) {
        Item found = finder.findSignature(list, searched);
        String text = found.getTextContent();
    }

    public void bestTen (Judgment list) {
        TreeMap<String, DataFinder.Aggregate> found = finder.bestJudges(list);
    }
}
