package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReferencedCourtCase {

    @SerializedName("caseNumber")
    @Expose
    private final String caseNumber;
    @SerializedName("judgmentIds")
    @Expose
    private final List<Integer> judgmentIds;
    @SerializedName("generated")
    @Expose
    private final boolean generated;

    public ReferencedCourtCase(Builder builder) {
        this.caseNumber = builder.caseNumber;
        this.judgmentIds = builder.judgmentIds;
        this.generated = builder.generated;
    }

    public static class Builder {

        private String caseNumber;
        private List<Integer> judgmentIds;
        private boolean generated;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setCaseNumber(String caseNumber) {
            this.caseNumber = caseNumber;
            return this;
        }

        public Builder setJudgmentIds(List<Integer> judgmentIds) {
            this.judgmentIds = judgmentIds;
            return this;
        }

        public Builder setGenerated(boolean generated) {
            this.generated = generated;
            return this;
        }

        public ReferencedCourtCase build() {
            return new ReferencedCourtCase(this);
        }
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public List<Integer> getJudgmentIds() {
        return judgmentIds;
    }

    public boolean isGenerated() {
        return generated;
    }
}
