package parser;

import com.google.common.collect.ImmutableMap;
import model.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HtmlParser {

    private Map<String, String> judgmentTypes = ImmutableMap.of("postanowienie", "DECISION", "uchwała", "RESOLUTION", "wyrok", "SENTENCE", "zarządzenie", "REGULATION", "uzasadnienie", "REASONS");
    private Map<String, String> specialRoles = ImmutableMap.of("przewodniczący", "PRESIDING_JUDGE", "sprawozdawca", "REPORTING_JUDGE", "autor", "REASONS_FOR_JUDGMENT_AUTHOR");
    private Map<String, String> courtTypes = ImmutableMap.of("sąd najwyższy", "SUPREME_COURT", "trybunał konstytucyjny", "CONSTITUTIONAL_TRIBUNAL", "krajowa izba odwoławcza", "NATIONAL_APPEAL_CHAMBER");

    public Judgment parse(Path path) {
        Judgment judgment = new Judgment();

        try {
            File html = new File(path.toString());
            Document htmlDoc = Jsoup.parse(html, "UTF-8", "");
            judgment = this.collect(judgment, htmlDoc);
        } catch (IOException err) {
            err.printStackTrace();
        }
        return judgment;
    }

    public Judgment collect(Judgment judgment, Document html) {
        Item object = Item.Builder.newInstance()
                .setCourtCases(this.getHtmlCaseNumber(html))
                .setJudgmentType(this.getHtmlJudgmentType(html))
                .setJudges(this.getHtmlJudges(html))
                .setJudgmentDate(this.getHtmlJudgmentDate(html))
                .setReferencedRegulations(this.getHtmlReferencedRegulations(html))
                .setCourtType(this.getHtmlCourtType(html))
                .setReferencedCourtCases(this.getHtmlReferencedCourtCases(html))
                .setTextContent(this.getHtmlTextContent(html))
                .build();
        judgment.getItems().add(object);

        return judgment;
    }

    public List<CourtCase> getHtmlCaseNumber(Document html) {
        Elements warHeader = html.getElementsByClass("war_header");
        String[] splitting = warHeader.text().split("-");

        List<CourtCase> newList = new ArrayList<>();
        newList.add(new CourtCase(splitting[0].trim()));
        return newList;
    }

    public String getHtmlJudgmentType(Document html) {
        Elements warHeader = html.getElementsByClass("war_header");
        String[] splitting = warHeader.text().split("-");
        String[] moreSplitting = splitting[1].trim().split(" ");
        String judgmentType = judgmentTypes.get(moreSplitting[0].trim().toLowerCase());
        return judgmentType;
    }

    public List<Judge> getHtmlJudges(Document html) {
        Element table = html.select("table").get(3);
        Elements rows = table.getElementsByClass("lista-label");
        String[] judges = null;
        int i = 0;

        for (Element currentRow : rows) {
            if (currentRow.text().equals("Sędziowie")) {
                judges = table.getElementsByClass("info-list-value").get(i).html().split("<br>");
            } else
                i++;

        }
        List<Judge> newList = new ArrayList<>();

        if (judges == null)
            return newList;

        for (String currentJudge : judges) {
            List<String> transformedRoles = new ArrayList<>();
            String roles = StringUtils.substringBetween(currentJudge, "/");

            String[] splitForName = currentJudge.split("/");
            String name = splitForName[0].trim().replaceAll("[^\\p{L}\\p{Z}]", "");
            if (roles != null) {
                String[] splitRoles = roles.split(" ");
                for (String currentRole : splitRoles) {
                    transformedRoles.add(specialRoles.get(currentRole.trim().toLowerCase()));
                }
            }

            Judge newJudge = Judge.Builder.newInstance()
                    .setName(name)
                    .setFunction(null)
                    .setSpecialRoles(transformedRoles)
                    .build();

            newList.add(newJudge);
        }

        return newList;
    }

    public String getHtmlJudgmentDate(Document html) {
        Element table = html.select("table").get(3);
        Elements rows = table.getElementsByClass("lista-label");
        int i = 0;

        for (Element currentRow : rows) {
            if (currentRow.text().equals("Data orzeczenia")) {
                String[] dateSplit = table.getElementsByClass("info-list-value").get(i).text().split(" ");
                return dateSplit[0].trim();
            } else
                i++;
        }
        return "";
    }

    public List<ReferencedRegulation> getHtmlReferencedRegulations(Document html) {
        Element table = html.select("table").get(3);
        Element row = table.getElementsByClass("info-list-value").last();
        Elements regulations = row.select("[href]");

        List<ReferencedRegulation> newList = new ArrayList<>();

        if (regulations == null)
            return newList;

        for (Element regulation : regulations) {
            String title = regulation.parents().select("[class=nakt]").text();
            String numbers = regulation.childNode(0).toString();
            String art = StringUtils.substringBetween(regulation.parents().html(), "</a>", "<br>");
            String yearString = StringUtils.substringBetween(regulation.text(), "U.", "nr").replaceAll("[^0-9]", "").trim();
            String noString = StringUtils.substringBetween(regulation.text(), "nr", "poz").replaceAll("[^0-9]", "").trim();
            String[] split = numbers.split(" ");
            String entryString = split[split.length - 1];
            int year = Integer.parseInt(yearString);
            int no = Integer.parseInt(noString);
            int entry = Integer.parseInt(entryString);

            if (art == null)
                art = "";

            ReferencedRegulation newRegulations = ReferencedRegulation.Builder.newInstance()
                    .setJournalTitle(title)
                    .setJournalYear(year)
                    .setJournalNo(no)
                    .setJournalEntry(entry)
                    .setText(title + "(" + numbers + art + ")")
                    .build();
            newList.add(newRegulations);
        }
        return newList;
    }

    public String getHtmlCourtType(Document html) {
        Element table = html.select("table").get(3);
        Elements rows = table.getElementsByClass("lista-label");
        String court = null;
        int i = 0;

        for (Element currentRow : rows) {
            if (currentRow.text().equals("Sąd")) {
                court = table.getElementsByClass("info-list-value").get(i).text();
            } else
                i++;
        }

        String courtType = courtTypes.get(court.toLowerCase());

        if (courtType == null)
            courtType = "COMMON_COURT";

        return courtType;
    }

    public List<ReferencedCourtCase> getHtmlReferencedCourtCases(Document html) {
        Element table = html.select("table").get(3);
        Elements rows = table.getElementsByClass("lista-label");
        String[] courtCases = null;
        int i = 0;

        for (Element currentRow : rows) {
            if (currentRow.text().equals("Sygn. powiązane")) {
                courtCases = table.getElementsByClass("info-list-value").get(i).html().split("<br>");
            } else
                i++;

        }

        List<ReferencedCourtCase> newList = new ArrayList<>();

        if (courtCases == null)
            return newList;

        for (String currentCase : courtCases) {
            String[] splitted = currentCase.split("-");
            List<Integer> emptyList = new ArrayList<>();

            ReferencedCourtCase object = ReferencedCourtCase.Builder.newInstance()
                    .setCaseNumber(splitted[0].trim())
                    .setJudgmentIds(emptyList)
                    .setGenerated(true)
                    .build();

            newList.add(object);
        }

        return newList;
    }

    public String getHtmlTextContent(Document html) {
        Element table = html.select("table").get(3);
        Elements rows = table.getElementsByClass("info-list-value-uzasadnienie");
        String text = "";

        for (Element currentRow : rows) {
            text = text + rows.text() + " ";
        }

        return text.trim();
    }

}
