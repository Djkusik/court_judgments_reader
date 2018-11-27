package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferencedRegulation {

    @SerializedName("journalTitle")
    @Expose
    private final String journalTitle;
    @SerializedName("journalNo")
    @Expose
    private final int journalNo;
    @SerializedName("journalYear")
    @Expose
    private final int journalYear;
    @SerializedName("journalEntry")
    @Expose
    private final int journalEntry;
    @SerializedName("text")
    @Expose
    private final String text;

    public ReferencedRegulation(Builder builder) {
        this.journalTitle = builder.journalTitle;
        this.journalNo = builder.journalNo;
        this.journalYear = builder.journalYear;
        this.journalEntry = builder.journalEntry;
        this.text = builder.text;
    }

    public static class Builder {

        private String journalTitle;
        private int journalNo;
        private int journalYear;
        private int journalEntry;
        private String text;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setJournalTitle(String journalTitle) {
            this.journalTitle = journalTitle;
            return this;
        }

        public Builder setJournalNo(int journalNo) {
            this.journalNo = journalNo;
            return this;
        }

        public Builder setJournalYear(int journalYear) {
            this.journalYear = journalYear;
            return this;
        }

        public Builder setJournalEntry(int journalEntry) {
            this.journalEntry = journalEntry;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public ReferencedRegulation build() {
            return new ReferencedRegulation(this);
        }
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public int getJournalNo() {
        return journalNo;
    }

    public int getJournalYear() {
        return journalYear;
    }

    public int getJournalEntry() {
        return journalEntry;
    }

    public String getText() {
        return text;
    }
}
