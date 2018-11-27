package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("id")
    @Expose
    private final String id;
    @SerializedName("courtType")
    @Expose
    private final String courtType;
    @SerializedName("courtCases")
    @Expose
    private final List<CourtCase> courtCases;
    @SerializedName("judgmentType")
    @Expose
    private final String judgmentType;
    @SerializedName("judges")
    @Expose
    private final List<Judge> judges;
    @SerializedName("source")
    @Expose
    private final Source source;
    @SerializedName("courtReporters")
    @Expose
    private final List<String> courtReporters;
    @SerializedName("decision")
    @Expose
    private final String decision;
    @SerializedName("summary")
    @Expose
    private final String summary;
    @SerializedName("textContent")
    @Expose
    private final String textContent;
    @SerializedName("legalBases")
    @Expose
    private final List<String> legalBases;
    @SerializedName("referencedRegulations")
    @Expose
    private final List<ReferencedRegulation> referencedRegulations;
    @SerializedName("keywords")
    @Expose
    private final List<String> keywords;
    @SerializedName("referencedCourtCases")
    @Expose
    private final List<ReferencedCourtCase> referencedCourtCases;
    @SerializedName("receiptDate")
    @Expose
    private final String receiptDate;
    @SerializedName("meansOfAppeal")
    @Expose
    private final String meansOfAppeal;
    @SerializedName("judgmentResult")
    @Expose
    private final String judgmentResult;
    @SerializedName("lowerCourtJudgments")
    @Expose
    private final List<String> lowerCourtJudgments;
    @SerializedName("judgmentDate")
    @Expose
    private final String judgmentDate;

    public Item(Builder builder) {
        this.id = builder.id;
        this.courtType = builder.courtType;
        this.courtCases = builder.courtCases;
        this.judgmentType = builder.judgmentType;
        this.judges = builder.judges;
        this.source = builder.source;
        this.courtReporters = builder.courtReporters;
        this.decision = builder.decision;
        this.summary = builder.summary;
        this.textContent = builder.textContent;
        this.legalBases = builder.legalBases;
        this.referencedRegulations = builder.referencedRegulations;
        this.keywords = builder.keywords;
        this.referencedCourtCases = builder.referencedCourtCases;
        this.receiptDate = builder.receiptDate;
        this.meansOfAppeal = builder.meansOfAppeal;
        this.judgmentResult = builder.judgmentResult;
        this.lowerCourtJudgments = builder.lowerCourtJudgments;
        this.judgmentDate = builder.judgmentDate;
    }

    public static class Builder {

        private String id;
        private String courtType;
        private List<CourtCase> courtCases = null;
        private String judgmentType;
        private List<Judge> judges = null;
        private Source source;
        private List<String> courtReporters = null;
        private String decision;
        private String summary;
        private String textContent;
        private List<String> legalBases;
        private List<ReferencedRegulation> referencedRegulations = null;
        private List<String> keywords = null;
        private List<ReferencedCourtCase> referencedCourtCases = null;
        private String receiptDate;
        private String meansOfAppeal;
        private String judgmentResult;
        private List<String> lowerCourtJudgments = null;
        private String judgmentDate;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCourtType(String courtType) {
            this.courtType = courtType;
            return this;
        }

        public Builder setCourtCases(List<CourtCase> courtCases) {
            this.courtCases = courtCases;
            return this;
        }

        public Builder setJudgmentType(String judgmentType) {
            this.judgmentType = judgmentType;
            return this;
        }

        public Builder setJudges(List<Judge> judges) {
            this.judges = judges;
            return this;
        }

        public Builder setSource(Source source) {
            this.source = source;
            return this;
        }

        public Builder setCourtReporters(List<String> courtReporters) {
            this.courtReporters = courtReporters;
            return this;
        }

        public Builder setDecision(String decision) {
            this.decision = decision;
            return this;
        }

        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder setTextContent(String textContent) {
            this.textContent = textContent;
            return this;
        }

        public Builder setLegalBases(List<String> legalBases) {
            this.legalBases = legalBases;
            return this;
        }

        public Builder setReferencedRegulations(List<ReferencedRegulation> referencedRegulations) {
            this.referencedRegulations = referencedRegulations;
            return this;
        }

        public Builder setKeywords(List<String> keywords) {
            this.keywords = keywords;
            return this;
        }

        public Builder setReferencedCourtCases(List<ReferencedCourtCase> referencedCourtCases) {
            this.referencedCourtCases = referencedCourtCases;
            return this;
        }

        public Builder setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
            return this;
        }

        public Builder setMeansOfAppeal(String meansOfAppeal) {
            this.meansOfAppeal = meansOfAppeal;
            return this;
        }

        public Builder setJudgmentResult(String judgmentResult) {
            this.judgmentResult = judgmentResult;
            return this;
        }

        public Builder setLowerCourtJudgments(List<String> lowerCourtJudgments) {
            this.lowerCourtJudgments = lowerCourtJudgments;
            return this;
        }

        public Builder setJudgmentDate(String judgmentDate) {
            this.judgmentDate = judgmentDate;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getCourtType() {
        return courtType;
    }

    public List<CourtCase> getCourtCases() {
        return courtCases;
    }

    public String getJudgmentType() {
        return judgmentType;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public List<String> getCourtReporters() {
        return courtReporters;
    }

    public String getDecision() {
        return decision;
    }

    public String getSummary() {
        return summary;
    }

    public String getTextContent() {
        return textContent;
    }

    public List<String> getLegalBases() {
        return legalBases;
    }

    public List<ReferencedRegulation> getReferencedRegulations() {
        return referencedRegulations;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<ReferencedCourtCase> getReferencedCourtCases() {
        return referencedCourtCases;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public String getMeansOfAppeal() {
        return meansOfAppeal;
    }

    public String getJudgmentResult() {
        return judgmentResult;
    }

    public List<String> getLowerCourtJudgments() {
        return lowerCourtJudgments;
    }

    public String getJudgmentDate() {
        return judgmentDate;
    }
}
